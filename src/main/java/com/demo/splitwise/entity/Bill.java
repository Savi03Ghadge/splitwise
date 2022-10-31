package com.demo.splitwise.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @NotNull
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "billType")
    private String billType;

    @Column(name="createdBy")
    private long createdBy;

    @NotNull
    @Column(name = "createdDate")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Set<Histories> histories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Set<Debts> debts = new HashSet<>();

    public Bill () {

    }

    public Bill(Long billId, Double amount, String billType, long createdBy) {
        this.billId = billId;
        this.amount = amount;
        this.billType = billType;
        this.createdBy = createdBy;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Set<Histories> getHistories() {
        return histories;
    }

    public void setHistories(Set<Histories> histories) {
        this.histories = histories;
    }

    public Set<Debts> getDebts() {
        return debts;
    }

    public void setDebts(Set<Debts> debts) {
        this.debts = debts;
    }
}
