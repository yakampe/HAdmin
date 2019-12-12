package co.uk.yktech.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.uk.yktech.models.Payment;
import co.uk.yktech.services.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@GetMapping("/")
	public ResponseEntity<List<Payment>> getAllPayments(){
		return paymentService.getAllPayments();
	}
	
	@GetMapping("/{moneyTransactionId}")
	public ResponseEntity<Payment> getPaymentForMoneyTransaction(
			@PathVariable("moneyTransactionId") Long moneyTransactionId){
		return paymentService.getPaymentByMoneyTransactionId(moneyTransactionId);
	}

	@PostMapping("/")
	public ResponseEntity<Payment> createPaymentReferenceForTransaction(
			@RequestParam("pid") Long payerId,
			@RequestParam("mid") Long moneyTransactionId,
			@RequestParam("ttp") BigDecimal totalToPay,
			@RequestParam("notes") String notes
			) {
		return paymentService.createPaymentReferenceForTransaction(payerId, moneyTransactionId, totalToPay, notes);
	}

}
