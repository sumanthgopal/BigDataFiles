/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.validator;

import com.me.pojo.Login;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Nirali
 */
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.isAssignableFrom(Login.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "username cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "password cannot be blank");
    }
    
    
}
