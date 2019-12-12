package co.uk.yktech.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.TransactionTypeCategory;

public interface TransactionTypeCategoryRepository extends CrudRepository<TransactionTypeCategory, Long> {
	
	@Query(value = "from TransactionTypeCategory t where t.categoryName = :categoryName")
	TransactionTypeCategory getTransactionTypeCategoryByName(String categoryName);

}
