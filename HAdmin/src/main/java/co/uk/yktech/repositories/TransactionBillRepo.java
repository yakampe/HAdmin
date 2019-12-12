package co.uk.yktech.repositories;

import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.TransactionBill;

public interface TransactionBillRepo extends CrudRepository<TransactionBill, Long> {

	TransactionBill findFirstByOrderByIdDesc();


}
