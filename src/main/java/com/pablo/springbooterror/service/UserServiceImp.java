package com.pablo.springbooterror.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pablo.springbooterror.models.domain.User;

@Service
public class UserServiceImp implements UserService{

    private List<User> users;

    public UserServiceImp(){
        this.users = new ArrayList<User>();
        users.add(new User(1L, "PAblo", "Santillana"));
        users.add(new User(2L, "Juan", "¨PErez"));
        users.add(new User(3L, "Sanchez", "Sant"));
        users.add(new User(4L, "Ramires", "Sanchez"));
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(p->p.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
    
}
