package com.demo.splitwise.controller;

import com.demo.splitwise.entity.Users;
import com.demo.splitwise.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UsersService usersService = null;

    @Autowired
    UserController(UsersService usersService ) { this.usersService = usersService; }

    @PostMapping("/user")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        try {
            Users userCreated = usersService.create(user);
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create user");
        }
    }

    @GetMapping("/users/user/{id}")
    public ResponseEntity<Users> fetchUserById(@PathVariable(value = "id") Long userId){
        Users user = usersService.findUser(userId);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<Users>> fetchAllUsers() {
        List<Users> all = usersService.findAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Users> deleteUserById(@PathVariable(value = "id") Long userId) {
        Users userDeleted = usersService.delete(userId);
        return new ResponseEntity<>(userDeleted, HttpStatus.OK);
    }



}
