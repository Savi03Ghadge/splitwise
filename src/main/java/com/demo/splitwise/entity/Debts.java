package com.demo.splitwise.entity;

import com.demo.splitwise.model.SettlementStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Debts")
public class Debts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id")
    private long debt_id;

    @NotNull
    @Column(name = "payer")
    private long payer;

    @NotNull
    @Column(name = "debtAmount")
    private double debtAmount;

    @Column(name = "amountPaid")
    private double amountPaid;

    @Column(name = "remainingDebt")
    private double remainingDebt;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "payee")
    private long payee;

    @Column(name = "bill_id")
    private long bill_id;

    @Column(name = "SettlementStatus")
    private SettlementStatus status;

    public  Debts () {

    }

    public Debts(long debt_id, long payer, double debtAmount, double amountPaid, double remainingDebt,
                 Date createdAt, Date updatedAt, long payee,
                 long bill_id, SettlementStatus status) {
        this.debt_id = debt_id;
        this.payer = payer;
        this.debtAmount = debtAmount;
        this.amountPaid = amountPaid;
        this.remainingDebt = remainingDebt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.payee = payee;
        this.bill_id = bill_id;
        this.status = status;
    }

    public long getDebt_id() {
        return debt_id;
    }

    public void setDebt_id(long debt_id) {
        this.debt_id = debt_id;
    }

    public long getPayer() {
        return payer;
    }

    public void setPayer(long payer) {
        this.payer = payer;
    }


    public long getBill_id() {
        return bill_id;
    }

    public void setBill_id(long bill_id) {
        this.bill_id = bill_id;
    }

    public long getPayee() {
        return payee;
    }

    public void setPayee(long payee) {
        this.payee = payee;
    }

    public double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(double remainingDebt) {
        this.remainingDebt = remainingDebt;
    }

    public SettlementStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
