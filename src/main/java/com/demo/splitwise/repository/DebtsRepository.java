package com.demo.splitwise.repository;

import com.demo.splitwise.entity.Debts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtsRepository extends JpaRepository<Debts, Long> {
    @Query("select debt from Debts debt where debt.payer = :payer and bill_id = :billId")
    Debts findByPayerAndBill(@Param("payer") Long payer, @Param("billId") Long billId);

    @Query("select debt from Debts debt where debt.payer = :payer")
    List<Debts> findByPayer(@Param("payer") Long payer);

    @Query("select debt from Debts debt where debt.payee = :payee")
    List<Debts> findByPayee(@Param("payee") Long payee);
}
