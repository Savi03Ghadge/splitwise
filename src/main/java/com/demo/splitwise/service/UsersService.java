package com.demo.splitwise.service;

import com.demo.splitwise.entity.Users;
import com.demo.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository = null;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users create (Users user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        Users savedUser = usersRepository.save(user);
        return savedUser;
    }

    public Users findUser(Long userId) {
        Users user = usersRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found :: " + userId));
        return user;
    }

    public Users findUserByName( String userName) {
        Users user = usersRepository.findByName(userName);
        return user;
    }

    public Users findUserByEmail( String email) {
        Users user = usersRepository.findByEmail(email);
        return user;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users delete(Long userId) {
        Optional<Users> user = usersRepository.findById(userId);
        usersRepository.delete(user.get());
        return user.get();
    }
}
