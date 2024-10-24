package com.ra.base_spring_mvc.validator.voucher;

import com.ra.base_spring_mvc.model.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class VoucherCodeUniqueImpl implements ConstraintValidator<VoucherCodeUnique,String> {
    @Autowired
    private VoucherService voucherService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !voucherService.CheckCodeExist(s);
    }
}
