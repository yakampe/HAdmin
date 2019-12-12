package co.uk.yktech.services;

import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {

}
