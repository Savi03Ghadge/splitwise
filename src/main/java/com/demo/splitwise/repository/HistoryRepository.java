package com.demo.splitwise.repository;

import com.demo.splitwise.entity.Histories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<Histories, Long> {

    @Query("select payment from Histories payment where payment.payer = :payer")
    List<Histories> findByPayer(@Param("payer") Long payer);

    @Query("select payment from Histories payment where payment.payee = :payee")
    List<Histories> findByPayee(@Param("payee") Long payee);
}
