package com.ra.base_spring_mvc.validator.voucher;

import com.ra.base_spring_mvc.validator.category.CategoryNameUniqueImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VoucherCodeUniqueImpl.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VoucherCodeUnique {
    String message() default "Code voucher existed !" ;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {} ;
}
