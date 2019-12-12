package co.uk.yktech.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
	
	@Query(value = "from TransactionType t where t.typeName = :typeName")
	TransactionType getEntityByTypeName(String typeName);
}
