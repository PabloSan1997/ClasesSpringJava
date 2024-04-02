package com.pablo.spirngboot.springbootcrudjpa;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pablo.spirngboot.springbootcrudjpa.entities.Product;

@Component
public class ProductValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
       return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"No puede ser nombre nulo");
        if(product.getDescription()==null || product.getDescription().isEmpty()){
            errors.rejectValue("description", null,"Tiene que haber una descripcion");
        }

        if(product.getPrice()==null){
            errors.rejectValue("price", null,"El precio es requerido");
        }
        else if(product.getPrice()<500){
            errors.rejectValue("price", null,"No se pueden valores menores a 500");
        }
    }
    
}
