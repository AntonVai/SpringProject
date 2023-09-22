package com.javaKava.SpringProject.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValid.class)
public @interface UniqueEmail {
    String message() default "{this email is already in use}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
