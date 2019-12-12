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

	public TransactionTypeCategory newEntity(TransactionTypeCategory ttc) {
		return transactionTypeCategoryRepo.save(ttc);
	}

	public TransactionTypeCategory getEntityById(Long id) {
		return transactionTypeCategoryRepo.findById(id).get();
	}

	public TransactionTypeCategory getEntityByCategoryName(String categoryName) {
		return transactionTypeCategoryRepo.getTransactionTypeCategoryByName(categoryName);
	}

	public void deleteEntityById(Long id) {
		transactionTypeCategoryRepo.deleteById(id);
	}

	public TransactionTypeCategory updateEntityById(Long id, TransactionTypeCategory ttc) {
		if(transactionTypeCategoryRepo.findById(id).get() != null) {
			ttc.setId(id);
			return transactionTypeCategoryRepo.save(ttc);			
		} else {
			return null;
		}
		
	}

	
}
