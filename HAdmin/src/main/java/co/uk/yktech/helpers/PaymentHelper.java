package co.uk.yktech.helpers;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.models.Payment;
import co.uk.yktech.models.Person;
import co.uk.yktech.repositories.MoneyTransactionRepo;
import co.uk.yktech.repositories.PaymentRepository;
import co.uk.yktech.repositories.PersonRepository;

@Service
public class PaymentHelper {
	@Autowired
	PersonRepository personRepo;
	@Autowired
	MoneyTransactionRepo moneyTransactionRepo;
	@Autowired
	PaymentRepository paymentRepo;

	public Payment newTransactionPayment(Long payerId, Long moneyTransactionId, BigDecimal totalToPay, String notes) {
		try {
			Payment newPayment = new Payment();
			Person payee = personRepo.findById(payerId).get();
			MoneyTransaction mt = moneyTransactionRepo.findById(moneyTransactionId).get();

			newPayment.setPayee(payee);
			newPayment.setTransaction(mt);
			newPayment.setTotalToPay(totalToPay);
			newPayment.setNotes(notes);

			paymentRepo.save(newPayment);
			
			Set<Payment> newPayments = mt.getPayments();
			newPayments.add(newPayment);
			mt.setPayments(newPayments);
			moneyTransactionRepo.save(mt);
			
			newPayments.clear();
			newPayments.addAll(payee.getPayments());
			newPayments.add(newPayment);
			payee.setPayments(newPayments);
			personRepo.save(payee);
			return newPayment;

		} catch (Exception e) {
			return null;
		}
	}

	public Payment updateTransactionPayment(Payment payment, BigDecimal totalToPay, String notes) {
		try {

			payment.setTotalToPay(totalToPay);
			payment.setNotes(notes);

			paymentRepo.save(payment);

			return payment;

		} catch (Exception e) {
			return null;
		}
	}

}
