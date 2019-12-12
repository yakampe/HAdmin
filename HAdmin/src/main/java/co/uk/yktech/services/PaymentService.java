package co.uk.yktech.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.uk.yktech.helpers.PaymentHelper;
import co.uk.yktech.models.Payment;
import co.uk.yktech.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	PaymentHelper paymentHelper;
	
	public ResponseEntity<Payment> getPaymentByMoneyTransactionId(Long id){
		try {
			return new ResponseEntity<>(paymentRepo.findById(id).get(), HttpStatus.OK);			
		} catch (java.util.NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Payment> createPaymentReferenceForTransaction(Long payerId, Long moneyTransactionId, BigDecimal totalToPay, String notes) {
		//see if payment already exists
		Payment query = paymentRepo.findByTransactionAndPerson(payerId, moneyTransactionId);
		//if no - create
		if(query == null) {
			query = paymentHelper.newTransactionPayment(payerId, moneyTransactionId, totalToPay, notes);
			return new ResponseEntity<>(query, HttpStatus.CREATED);
		} else {
			query = paymentHelper.updateTransactionPayment(query, totalToPay, notes);
			return new ResponseEntity<>(query, HttpStatus.OK);
		}
		
	}

	public ResponseEntity<List<Payment>> getAllPayments() {
		return new ResponseEntity<>((List<Payment>) paymentRepo.findAll(), HttpStatus.OK);
	}

}
