package co.uk.yktech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.TransactionTypeCategory;
import co.uk.yktech.repositories.TransactionTypeCategoryRepository;

@Service
public class TransactionTypeCategoryService {

	@Autowired
	TransactionTypeCategoryRepository transactionTypeCategoryRepo;
	
	public List<TransactionTypeCategory> getAllEntities() {
		return (List<TransactionTypeCategory>) transactionTypeCategoryRepo.findAll();
	}

	
}
