package com.demo.splitwise.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Histories")
public class Histories {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long transaction_id;

    @NotNull
    @Column(name = "amountPaid")
    private double amountPaid;

    @Column(name = "payer")
    private long payer;

    @Column(name = "payee")
    private long payee;

    @Column(name = "bill_id")
    private long bill_id;

    @Column(name = "createdAt")
    private Date createdAt;

//    @Column(name = "updatedAt")
//    private Date updatedAt;

    public Histories() {
    }

    public Histories(long transaction_id, double amountPaid,
                     long payee, long bill_id, long payer,
                     Date createdAt) {
        this.transaction_id = transaction_id;
        this.amountPaid = amountPaid;
        this.bill_id = bill_id;
        this.payee = payee;
        this.createdAt = createdAt;
        this.payer = payer;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public long getPayee() {
        return payee;
    }

    public void setPayee(long payee) {
        this.payee = payee;
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

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

//    public long getSupposedToPay() {
//        return supposedToPay;
//    }
//
//    public void setSupposedToPay(long supposedToPay) {
//        this.supposedToPay = supposedToPay;
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
