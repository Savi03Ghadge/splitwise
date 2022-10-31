package com.demo.splitwise.controller;

import com.demo.splitwise.entity.Bill;
import com.demo.splitwise.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    private BillService billService = null;

    @Autowired
    BillController(BillService billService){
        this.billService = billService;
    }

//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bill/user/{userId}")
    public ResponseEntity<Bill> createBill(@PathVariable(value = "userId") Long userId, @RequestBody Bill bill){
        try{
            Bill savedBill = billService.create(userId, bill);
            return new ResponseEntity<>(savedBill, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create bill");
        }
    }

//    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/bill/{id}")
    public ResponseEntity<Bill> fetchBillById(@PathVariable(value = "id") Long billId) {
        try{
            Bill bill = billService.findBill(billId);
            return new ResponseEntity<>(bill, HttpStatus.FOUND);
        } catch (Exception ex) {
            throw new RuntimeException("Record not found");
        }

    }

    @GetMapping("/bill/all")
    public ResponseEntity<List<Bill>> fetchAll() {
        List<Bill> allBills = billService.findAll();
        return new ResponseEntity<>(allBills, HttpStatus.FOUND);
    }

    @DeleteMapping("/bill/{id}")
    public ResponseEntity<Bill> deleteBillById(@PathVariable(value = "id") Long billId) {
        Bill deletedBill = billService.delete(billId);
        return new ResponseEntity<>(deletedBill, HttpStatus.OK);
    }



}
