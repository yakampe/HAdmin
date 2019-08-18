package co.uk.yktech.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import co.uk.yktech.models.TransactionBill;

public class TransactionBillService {

	public ResponseEntity<List<TransactionBill>> getAllBills() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<TransactionBill> getLatestBill() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<TransactionBill> getBillById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<TransactionBill> createBill(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Boolean> deleteBill(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Boolean> updateBill(TransactionBill transactionBill) {
		// TODO Auto-generated method stub
		return null;
	}

}
