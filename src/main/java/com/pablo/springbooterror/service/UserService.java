package com.pablo.springbooterror.service;

import java.util.List;

import com.pablo.springbooterror.models.domain.User;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
}
