package com.demo.splitwise.service;

import com.demo.splitwise.entity.Debts;
import com.demo.splitwise.entity.Histories;
import com.demo.splitwise.model.SettlementStatus;
import com.demo.splitwise.repository.DebtsRepository;
import com.demo.splitwise.repository.HistoryRepository;
import com.demo.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentHistoryService {

    private HistoryRepository historyRepository = null;

    private UsersRepository usersRepository = null;

    private DebtsRepository debtsRepository = null;

    @Autowired
    PaymentHistoryService (HistoryRepository historyRepository,
                           UsersRepository usersRepository, DebtsRepository debtsRepository) {

        this.usersRepository = usersRepository;
        this.historyRepository = historyRepository;
        this.debtsRepository = debtsRepository;
    }

    public Histories createPaymentRecord(Long userId, Histories payment) {
        Date date = new Date();
        payment.setCreatedAt(date);

        //Update Debts Record when payment is made
        Debts updatedDebt = updateSettlement(userId, payment);
        payment.setPayee(updatedDebt.getPayee());

        Histories createdRecord = usersRepository.findById(userId).map(user -> {
            user.getHistories().add(payment);
            return historyRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("User NOT found" + userId));

        return createdRecord;
    }

    public List<Histories> getAllPaymentRecords() {
        List<Histories> allPaymentHistories = historyRepository.findAll();
        return allPaymentHistories;
    }

    public List<Histories> getAllPaymentsAsPayer(Long userId) {
        List<Histories> userPayments = historyRepository.findByPayer(userId);
        return userPayments;
    }

    public List<Histories> getAllPaymentsAsPayee(Long userId) {
        List<Histories> payeePayments = historyRepository.findByPayee(userId);
        return payeePayments;
    }

    public Debts updateSettlement(Long userId, Histories payment) {
        Date date = new Date();

        Debts userDebt = debtsRepository.findByPayerAndBill(userId, payment.getBill_id());

        //Calculate partial amount and update status of debt
            Double amountPaid = userDebt.getAmountPaid() + payment.getAmountPaid();
            Double remainingAmt = userDebt.getDebtAmount() - amountPaid;
            userDebt.setAmountPaid(amountPaid);
            userDebt.setRemainingDebt(remainingAmt);
            userDebt.setUpdatedAt(date);
                if(userDebt.getDebtAmount() == amountPaid) {
                    userDebt.setStatus(SettlementStatus.PAID);
                } else if (userDebt.getAmountPaid() == 0) {
                    userDebt.setStatus(SettlementStatus.UNPAID);
                } else {
                    userDebt.setStatus(SettlementStatus.PARTIAL);
                }
        return debtsRepository.save(userDebt);
    }

    public Histories deletePaymentHistory(Long paymentId) {
        Optional<Histories> paymentHistory = historyRepository.findById(paymentId);
        historyRepository.delete(paymentHistory.get());
        return paymentHistory.get();
    }

}
