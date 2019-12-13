package co.uk.yktech.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.dto.MoneyTransactionDTO;
import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.repositories.MoneyTransactionRepo;

@Service
public class MoneyTransactionService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MoneyTransactionRepo moneyTransactionRepo;
	
    @Autowired
    private ModelMapper modelMapper;
	
	public MoneyTransactionDTO getTransactionById(Long id) {		
		return convertToDto(moneyTransactionRepo.findById(id).get());
	}

	public List<String> createTransactions(List<MoneyTransactionDTO> transactions) {
		List<String> responses = new ArrayList<>();
		transactions.forEach(transaction -> {
			try {
				moneyTransactionRepo.save(convertToEntity(transaction));
				responses.add(transaction.getDescription() +  " - OK");
			} catch (Exception e) {
				logger.error(e.toString());
				responses.add(transaction.getDescription() +  " - ERROR " + e.toString() + " @ " + transaction.toString());
			} 
		});
		return responses;
	}
	
	public List<String> createTransactionsWithPOJO(List<MoneyTransaction> transactions) {
		List<String> responses = new ArrayList<>();
		transactions.forEach(transaction -> {
			try {
				moneyTransactionRepo.save(transaction);
				responses.add(transaction.getDescription() +  " - OK");
			} catch (Exception e) {
				logger.error(e.toString());
				responses.add(transaction.getDescription() +  " - ERROR " + e.toString() + " @ " + transaction.toString());
			} 
		});
		return responses;
	}

	public List<MoneyTransactionDTO> getAllTransactions() {	
		return  ((List<MoneyTransaction>) moneyTransactionRepo.findAll())
				.stream()
				.map(mt -> {
					logger.info(mt.toString());
					return convertToDto(mt);
				})
				.collect(Collectors.toList());
	}

	public Boolean updateTransaction(MoneyTransactionDTO mtDto) {
		MoneyTransaction mt = moneyTransactionRepo.findById(mtDto.getId()).get();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(mtDto, mt);
		
		logger.info(modelMapper.getConfiguration().getPropertyCondition().toString());
		
		moneyTransactionRepo.save(mt);
		return true;
	}
	
	public Set<MoneyTransactionDTO> getTransactionsBetweenDates(String startDateString, String endDateString, boolean includeBilled){
		LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Set<MoneyTransaction> mt = new HashSet<>();
		

		if(includeBilled) {
			mt = moneyTransactionRepo.getAllBetweenDates(startDate, endDate);		
		} else {
			mt = moneyTransactionRepo.getNotBilledBetweenDates(startDate, endDate);			
		}
		
		return mt
				.stream()
				.map(obj -> convertToDto(obj))
				.collect(Collectors.toSet());
		
	}
	
	
	private MoneyTransactionDTO convertToDto(MoneyTransaction mt) {

		MoneyTransactionDTO mtDto = modelMapper.map(mt, MoneyTransactionDTO.class);
		mtDto.setTransactionType(mt.getTransactionType() != null ? mt.getTransactionType().getTypeName() : null);
		mtDto.setBillId(mt.getBill() != null ? mt.getBill().getId() : null);
		
		return mtDto;	
	}
	
	private MoneyTransaction convertToEntity(MoneyTransactionDTO mtDto) {
		MoneyTransaction mt = modelMapper.map(mtDto, MoneyTransaction.class);
		return mt;
	}

	@Transactional
	public void deleteEntityById(Long id) {
		moneyTransactionRepo.deleteById(id);
	}
	
	
}
