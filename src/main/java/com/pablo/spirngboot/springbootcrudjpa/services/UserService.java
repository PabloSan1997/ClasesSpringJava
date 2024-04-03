package com.pablo.spirngboot.springbootcrudjpa.services;

import java.util.List;

import com.pablo.spirngboot.springbootcrudjpa.entities.User;

public interface UserService {
    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
