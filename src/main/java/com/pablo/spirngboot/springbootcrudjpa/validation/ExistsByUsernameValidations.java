package com.pablo.spirngboot.springbootcrudjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.pablo.spirngboot.springbootcrudjpa.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsByUsernameValidations implements ConstraintValidator<ExistsByUsername, String>{

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(userService!=null){
            return !userService.existsByUsername(value);
        }
        return true;
    }
    
}
