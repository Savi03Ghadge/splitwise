package com.demo.splitwise.service;

import com.demo.splitwise.entity.Users;
import com.demo.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository = null;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Logic to get user  from db
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);

        } else {
            return new User(user.getEmail(), user.getPassword(),
                    new ArrayList<>());
        }
        //returning dummy object
//        return new User("admin", "password", new ArrayList<>());
    }
}
