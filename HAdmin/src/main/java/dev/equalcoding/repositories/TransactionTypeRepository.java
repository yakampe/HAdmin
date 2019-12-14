package dev.equalcoding.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.equalcoding.models.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
	
	@Query(value = "from TransactionType t where t.typeName = :typeName")
	TransactionType getEntityByTypeName(String typeName);
}
