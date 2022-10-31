package com.demo.splitwise.repository;

import com.demo.splitwise.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select e from Users e where e.name = :name")
    Users findByName(@Param("name") String name);

    @Query("select e from Users e where e.email = :email")
    Users findByEmail(String email);

}
