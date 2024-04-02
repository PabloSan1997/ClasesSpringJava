package com.pablo.spirngboot.springbootcrudjpa.respoistories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pablo.spirngboot.springbootcrudjpa.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Long>{
 
    Optional<Role> findByName(String name);
}
