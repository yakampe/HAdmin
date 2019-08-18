package co.uk.yktech.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.models.TransactionBill;
import co.uk.yktech.repositories.MoneyTransactionRepo;
import co.uk.yktech.repositories.TransactionBillRepo;

@Service
public class TransactionBillService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionBillRepo transactionBillRepo;

	@Autowired
	MoneyTransactionRepo moneyTransactionRepo;

	public ResponseEntity<List<TransactionBill>> getAllBills() {
		return ResponseEntity.status(200).body((List<TransactionBill>) transactionBillRepo.findAll());
	}

	public ResponseEntity<TransactionBill> getLatestBill() {
		return ResponseEntity.status(200).body(transactionBillRepo.findFirstByOrderByIdDesc());
	}

	public ResponseEntity<TransactionBill> getBillById(Long id) {
		return ResponseEntity.status(200).body(transactionBillRepo.findById(id).get());
	}

	public ResponseEntity<TransactionBill> createBill(String startDateString, String endDateString) {
		BigDecimal totalDebit = BigDecimal.valueOf(0);
		BigDecimal totalCredit = BigDecimal.valueOf(0);

		LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Set<MoneyTransaction> qualifyingTransactions = new HashSet<>();
		while (startDate.isBefore(endDate)) {
			for (MoneyTransaction mt : moneyTransactionRepo.findAllByDate(startDate)) {
				qualifyingTransactions.add(mt);
				if (mt.getDebit() != null) 
					totalDebit = totalDebit.add(mt.getDebit());
				if (mt.getCredit() != null)
					totalCredit = totalCredit.add(mt.getCredit());
			}
			
			
			startDate = startDate.plusDays(1);

		}
		TransactionBill tb = new TransactionBill();

		tb.setTransactions(qualifyingTransactions);
		tb.setDate(LocalDate.now());
		tb.setTotalDebit(totalDebit);
		tb.setTotalCredit(totalCredit);

		transactionBillRepo.save(tb);
		return ResponseEntity.status(200).body(tb);
	}

	public ResponseEntity<Boolean> deleteBill(Long id) {
		transactionBillRepo.deleteById(id);
		if (transactionBillRepo.findById(id) == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		else
			return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	public void updateBill(TransactionBill transactionBill) {
		transactionBillRepo.save(transactionBill);
	}

}
