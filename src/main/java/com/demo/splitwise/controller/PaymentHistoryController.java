package com.demo.splitwise.controller;

import com.demo.splitwise.entity.Histories;
import com.demo.splitwise.service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentHistoryController {

    private PaymentHistoryService paymentHistoryService = null;

    @Autowired
    PaymentHistoryController(PaymentHistoryService paymentHistoryService) {
        this.paymentHistoryService = paymentHistoryService;
    }

    @PostMapping("/payment/payer/{userId}")
    public ResponseEntity<Histories> makePayment(@PathVariable(value = "userId") Long userId,
                                                 @RequestBody Histories payment) {
        try{
            Histories paidRecord = paymentHistoryService.createPaymentRecord(userId, payment);
            return new ResponseEntity<>(paidRecord, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException("Payment Failed");
        }
    }

    @GetMapping("/payment/histories")
    public ResponseEntity<List<Histories>> getAllRecords() {
        try {
            List<Histories> paymentHistories = paymentHistoryService.getAllPaymentRecords();
            return new ResponseEntity<>(paymentHistories, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Could not fetch payment records");
        }
    }

    @GetMapping("/payment/payer/histories/{userId}")
    public ResponseEntity<List<Histories>> getAllPayerHistories(@PathVariable(value = "userId") Long userId) {
        try {
            List<Histories> payerHistories = paymentHistoryService.getAllPaymentsAsPayer(userId);
            return new ResponseEntity<>(payerHistories, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Could not fetch payment records by user");
        }
    }

    @GetMapping("/payment/payee/histories/{userId}")
    public ResponseEntity<List<Histories>> getAllPayeeHistories(@PathVariable(value = "userId") Long userId) {
        try {
            List<Histories> payeeHistories = paymentHistoryService.getAllPaymentsAsPayee(userId);
            return new ResponseEntity<>(payeeHistories, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Could not find payment records for user");
        }
    }

    //Not in use for now
    @DeleteMapping("/payment/record/{recordId}")
    public ResponseEntity<Histories> deletePaymentHistory(@PathVariable(value = "recordId") Long recordId) {
        try{
            Histories payment = paymentHistoryService.deletePaymentHistory(recordId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot delete payment record");
        }
    }
}
