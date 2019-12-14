package dev.equalcoding.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.equalcoding.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
	
	@Query(value = "from Payment p where payee.id = :personId AND transaction.id = :transactionId")
	Payment findByTransactionAndPerson(Long personId, Long transactionId);

}
