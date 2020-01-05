package com.berger.angular.picshare.repository;

import com.berger.angular.picshare.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User,Long> {

    User findByMailAndPassword(String mail ,String password);
    
}