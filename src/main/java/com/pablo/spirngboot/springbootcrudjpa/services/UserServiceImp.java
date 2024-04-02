package com.pablo.spirngboot.springbootcrudjpa.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.spirngboot.springbootcrudjpa.entities.Role;
import com.pablo.spirngboot.springbootcrudjpa.entities.User;
import com.pablo.spirngboot.springbootcrudjpa.respoistories.RoleRepository;
import com.pablo.spirngboot.springbootcrudjpa.respoistories.UserRepository;

@Service
public class UserServiceImp implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRoleuser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();

        optionalRoleuser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return userRepository.save(user);
    }

}
