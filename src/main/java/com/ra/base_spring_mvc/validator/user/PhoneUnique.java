package com.ra.base_spring_mvc.validator.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneUniqueImpl.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneUnique {
    String message() default "Phone number existed !" ;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {} ;
}
