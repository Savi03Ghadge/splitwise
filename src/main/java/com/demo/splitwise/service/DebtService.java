package com.demo.splitwise.service;

import com.demo.splitwise.entity.Debts;
import com.demo.splitwise.repository.DebtsRepository;
import com.demo.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DebtService {

    private UsersRepository usersRepository = null;

    private DebtsRepository debtsRepository = null;

    @Autowired
    DebtService (UsersRepository usersRepository, DebtsRepository debtsRepository) {
        this.usersRepository = usersRepository;
        this.debtsRepository = debtsRepository;
    }

    public Debts createDebtsRecord(Long userId, Debts debts) {

        Date date = new Date();
        debts.setCreatedAt(date);
        
        Debts savedDebts = usersRepository.findById(userId).map(users -> {
               users.getDebts().add(debts) ;
               return debtsRepository.save(debts);
        }).orElseThrow(() -> new RuntimeException("User NOT found" + userId));

        return savedDebts;
    }

    public List<Debts> getAllDebts() {
        List<Debts> debtsList = debtsRepository.findAll();
        return debtsList;
    }

    public List<Debts> getAllPayerDebts(Long userId) {
        List<Debts> debtsList = debtsRepository.findByPayer(userId);
        return debtsList;
    }

    public List<Debts> getAllPayeeDebts(Long userId) {
        List<Debts> debtsList = debtsRepository.findByPayee(userId);
        return debtsList;
    }

    public Debts findPayerDebtsByBill(Long userId, Long billId) {
        Debts debtRecord = debtsRepository.findByPayerAndBill(userId, billId);
        return debtRecord;
    }

    public Debts updateDebtRecord (Debts debt) {
        Date date = new Date();
        debt.setUpdatedAt(date);
        Debts debtRecord = debtsRepository.save(debt);
        return debtRecord;
    }

    public Debts deleteDebtRecord (Long debtId) {
        Optional<Debts> debtRecord = debtsRepository.findById(debtId);
        debtsRepository.delete(debtRecord.get());
        return debtRecord.get();
    }



}
