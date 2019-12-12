package co.uk.yktech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.TransactionType;
import co.uk.yktech.repositories.TransactionTypeRepository;

@Service
public class TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepo;
	
	
	public List<TransactionType> getAllEntities() {
		return (List<TransactionType>) transactionTypeRepo.findAll();
	}


	public TransactionType newEntity(TransactionType tt) {
		return transactionTypeRepo.save(tt);
	}


	public TransactionType getEntityById(Long id) {
		return transactionTypeRepo.findById(id).get();
	}


	public TransactionType getEntityByTypeName(String typeName) {
		return transactionTypeRepo.getEntityByTypeName(typeName);
	}


	public void deleteEntityById(Long id) {
		transactionTypeRepo.deleteById(id);
	}


	public TransactionType updateEntityById(Long id, TransactionType tt) {
		if(transactionTypeRepo.findById(id).get() != null) {
			tt.setId(id);
			return transactionTypeRepo.save(tt);			
		} else {
			return null;
		}		
	}

}
