package com.example.MaslyakBank_client.validator.annotations;


import com.example.MaslyakBank_client.validator.PasswordAnnotationValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target ({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PasswordAnnotationValidator.class)
public @interface Password {

    String message() default "Not Valid Password";
    Class<?>[] groups() default { };
    Class<? extends jakarta.validation.Payload>[] payload() default { };

    int min() default 8;
    int max() default 20;



}
