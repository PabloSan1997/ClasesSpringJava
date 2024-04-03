package com.pablo.spirngboot.springbootcrudjpa.respoistories;

import org.springframework.data.repository.CrudRepository;

import com.pablo.spirngboot.springbootcrudjpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
    
}
