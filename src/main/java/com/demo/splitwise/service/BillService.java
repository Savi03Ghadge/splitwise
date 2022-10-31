package com.demo.splitwise.service;

import com.demo.splitwise.entity.Bill;
import com.demo.splitwise.entity.Debts;

import com.demo.splitwise.model.SettlementStatus;
import com.demo.splitwise.repository.BillRepository;
import com.demo.splitwise.repository.DebtsRepository;
import com.demo.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BillService {

    private BillRepository billRepository = null;

    private UsersRepository usersRepository = null;

//    private DebtService debtService = null;
    private DebtsRepository debtsRepository = null;

    @Autowired
    BillService (BillRepository billRepository, UsersRepository usersRepository, DebtsRepository debtsRepository){
        this.billRepository = billRepository;
        this.usersRepository = usersRepository;
        this.debtsRepository = debtsRepository;
    }


    public Bill create(Long userId, Bill bill) {

        Date date = new Date();
        bill.setCreatedDate(date);

        Set<Debts> debtsSet = bill.getDebts();
        debtsSet.stream().forEach(debt -> {
            debt.setCreatedAt(date);
            debt.setStatus(SettlementStatus.UNPAID);
                usersRepository.findById(userId).map(users -> {
                    users.getDebts().add(debt);
                    return debtsRepository.save(debt);
                });
        });

        Bill savedBill = usersRepository.findById(userId).map(user ->{
            user.getBills().add(bill);
            return billRepository.save(bill);
        }).orElseThrow(() -> new RuntimeException("User NOT found" + userId));

        return savedBill;
    }

    public Bill findBill(Long billId) {
        Bill bill = billRepository
                .findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found on :: " + billId));
        return bill;
    }

    public List<Bill> findAll() {
        List<Bill> billList = billRepository.findAll();
        return billList;
    }

    public Bill delete(Long billId){
        Optional<Bill> bill = billRepository.findById(billId);
        billRepository.delete(bill.get());
        return bill.get();
    }


}
