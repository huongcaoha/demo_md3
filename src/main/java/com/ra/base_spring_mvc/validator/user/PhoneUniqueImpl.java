package com.ra.base_spring_mvc.validator.user;

import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class PhoneUniqueImpl implements ConstraintValidator<PhoneUnique,String> {
    @Autowired
    private UserService userService ;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.checkPhoneExist(s);
    }
}
