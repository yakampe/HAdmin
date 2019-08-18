package co.uk.yktech.repositories;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.MoneyTransaction;

public interface MoneyTransactionRepo extends CrudRepository<MoneyTransaction, Long> {

	Set<MoneyTransaction> findAllByDate(LocalDate searchDate);

}
