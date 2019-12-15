package dev.equalcoding.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.equalcoding.dto.TransactionBillDto;
import dev.equalcoding.models.MoneyTransaction;
import dev.equalcoding.models.Payment;
import dev.equalcoding.models.Person;
import dev.equalcoding.models.TransactionBill;
import dev.equalcoding.repositories.MoneyTransactionRepo;
import dev.equalcoding.repositories.PaymentRepository;
import dev.equalcoding.repositories.TransactionBillRepo;

@Service
public class TransactionBillService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionBillRepo transactionBillRepo;

	@Autowired
	MoneyTransactionRepo moneyTransactionRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	ModelMapper modelMapper;

	public List<TransactionBillDto> getAllBills() {
		return ((List<TransactionBill>) transactionBillRepo.findAll())
				.stream()
				.map(tb -> convertToDto(tb))
				.collect(Collectors.toList());
	}

	public TransactionBillDto getLatestBill() {
		return convertToDto(transactionBillRepo.findFirstByOrderByIdDesc());
	}

	public TransactionBillDto getBillById(Long id) {
		return convertToDto(transactionBillRepo.findById(id).get());
	}

	@Transactional
	public TransactionBillDto createBill(String startDateString, String endDateString) {
		BigDecimal totalDebit = BigDecimal.valueOf(0);
		BigDecimal totalCredit = BigDecimal.valueOf(0);

		Map<Person, BigDecimal> payerPayments = new HashMap<>();

		TransactionBill tb = new TransactionBill();
		transactionBillRepo.save(tb);

		LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Set<MoneyTransaction> qualifyingTransactions = new HashSet<>();
		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			for (MoneyTransaction mt : moneyTransactionRepo.findAllByDate(startDate)) {

				mt.getPayments().forEach(payment -> {
					if (!payerPayments.containsKey(payment.getPayee())) {
						payerPayments.put(payment.getPayee(), payment.getTotalToPay());
					} else {
						payerPayments.replace(payment.getPayee(),
								payerPayments.get(payment.getPayee()).add(payment.getTotalToPay()));
					}
				});
				mt.setBill(tb);
				moneyTransactionRepo.save(mt);
				qualifyingTransactions.add(mt);
				if (mt.getDebit() != null)
					totalDebit = totalDebit.add(mt.getDebit());
				if (mt.getCredit() != null)
					totalCredit = totalCredit.add(mt.getCredit());
			}

			startDate = startDate.plusDays(1);

		}

		Set<Payment> paymentsForBill = new HashSet<>();
		
		tb.setPayments(paymentsForBill);
		tb.setTransactions(qualifyingTransactions);
		tb.setDate(LocalDate.now());
		tb.setTotalDebit(totalDebit);
		tb.setTotalCredit(totalCredit);
		
		transactionBillRepo.save(tb);

		//TODO: add notes string.
		payerPayments.forEach((person, value) -> {
			Payment newPayment = new Payment(tb, person, value, "empty notes for now");
			paymentsForBill.add(newPayment);
			paymentRepo.save(newPayment);		
		});
		return convertToDto(tb);
	}

	@Transactional
	public ResponseEntity<Boolean> deleteBill(Long id) {
		TransactionBill deletingBill = transactionBillRepo.findById(id).get();
		if ( deletingBill == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		else
			deletingBill.getPayments().forEach(payment -> {
				payment.setBill(null);
				paymentRepo.save(payment);
				paymentRepo.delete(payment);
			});
			deletingBill.setTransactions(null);
			transactionBillRepo.save(deletingBill);
			transactionBillRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	public void updateBill(TransactionBillDto tbDto) {
		TransactionBill tb = transactionBillRepo.findById(tbDto.getId()).get();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(tbDto, tb);
		transactionBillRepo.save(tb);
	}
	
	private TransactionBillDto convertToDto(TransactionBill tb) {

		TransactionBillDto tbDto = modelMapper.map(tb, TransactionBillDto.class);
		
		return tbDto;	
	}
	
	private TransactionBill convertToEntity(TransactionBillDto tbDto) {
		TransactionBill tb = modelMapper.map(tbDto, TransactionBill.class);
				
		return tb;
	}

}
