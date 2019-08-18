package co.uk.yktech.repositories;

import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.MoneyTransaction;

public interface MoneyTransactionRepo extends CrudRepository<MoneyTransaction, Long> {

}
