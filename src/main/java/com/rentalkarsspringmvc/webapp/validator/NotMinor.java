package com.rentalkarsspringmvc.webapp.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target( {METHOD, FIELD, ANNOTATION_TYPE} )
@Retention(RUNTIME)
@Constraint(validatedBy = NotMinorValidator.class)
@Documented
public @interface NotMinor {

    public String message() default "{NotMinor.User.birthday.validation}" ;

    public Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload()
            default {};

}
