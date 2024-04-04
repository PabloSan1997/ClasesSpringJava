package com.pablo.spirngboot.springbootcrudjpa.respoistories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pablo.spirngboot.springbootcrudjpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
    
    Optional<User> findByUsername(String username);
}
