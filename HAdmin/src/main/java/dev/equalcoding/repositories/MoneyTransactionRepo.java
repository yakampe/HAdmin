package dev.equalcoding.repositories;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.equalcoding.models.MoneyTransaction;

public interface MoneyTransactionRepo extends CrudRepository<MoneyTransaction, Long> {

	Set<MoneyTransaction> findAllByDate(LocalDate searchDate);

	@Query(value = "from MoneyTransaction m where date BETWEEN :startDate AND :endDate order by date desc")
	Set<MoneyTransaction> getAllBetweenDates(LocalDate startDate, LocalDate endDate);

	@Query(value = "from MoneyTransaction m where bill is not null AND date BETWEEN :startDate AND :endDate order by date desc")
	Set<MoneyTransaction> getBilledBetweenDates(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "from MoneyTransaction m where bill is null AND date BETWEEN :startDate AND :endDate order by date desc")
	Set<MoneyTransaction> getNotBilledBetweenDates(LocalDate startDate, LocalDate endDate);
}
