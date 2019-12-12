package co.uk.yktech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.StatementType;
import co.uk.yktech.repositories.StatmentTypeRepository;

@Service
public class StatementTypeService {
	
	@Autowired
	StatmentTypeRepository statementTypeRepo;

	public void addStatementType(StatementType statementType) {
		statementTypeRepo.save(statementType);
	}

	public Iterable<StatementType> getStatementTypes() {
		return statementTypeRepo.findAll();
	}

	public StatementType getTypeById(String id) {
		return statementTypeRepo.findById(Long.valueOf(id)).get();
	}

	
	
}
