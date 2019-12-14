package dev.equalcoding.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.equalcoding.models.TransactionBill;

public interface TransactionBillRepo extends CrudRepository<TransactionBill, Long> {

	TransactionBill findFirstByOrderByIdDesc();


}
