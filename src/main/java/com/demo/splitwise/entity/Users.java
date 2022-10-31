package com.demo.splitwise.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @NotNull
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;


    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy")
    private Set<Bill> bills = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer")
    private Set<Histories> histories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payee")
    private Set<Debts> debts = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private Set<Debts> payees = new HashSet<>();

    public Users () {

    }

    public Users(Long userId, String name, String email, String phone, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
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

    public void setDebts(Set<Debts> payer) {
        this.debts = debts;
    }

//    public Set<Debts> getPayees() {
//        return payees;
//    }
//
//    public void setPayees(Set<Debts> payees) {
//        this.payees = payees;
//    }
}
