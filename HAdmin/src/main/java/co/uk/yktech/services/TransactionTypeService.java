package co.uk.yktech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.TransactionType;

@Service
public class TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepo;
	
	
	public List<TransactionType> getAllEntities() {
		return (List<TransactionType>) transactionTypeRepo.findAll();
	}

}
