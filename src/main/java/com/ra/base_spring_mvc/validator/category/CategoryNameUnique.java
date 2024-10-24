package com.ra.base_spring_mvc.validator.category;

import com.ra.base_spring_mvc.validator.user.PhoneUniqueImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoryNameUniqueImpl.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryNameUnique {
    String message() default "Category name existed !" ;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {} ;
}
