package com.ra.base_spring_mvc.validator.user;

import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class UsenameUniqueImpl implements ConstraintValidator<UsernameUnique,String> {
    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.checkUsernameExist(s);
    }
}
