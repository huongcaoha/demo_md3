package com.ra.base_spring_mvc.validator.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsenameUniqueImpl.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {
    String message() default "Username existed !" ;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {} ;
}
