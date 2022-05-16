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
@Constraint(validatedBy = Before2DaysValidator.class)
@Documented
public @interface Before2Days {

    public String message() default "{Before2Days.Rent.startDate.validation}" ;

    public Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload()
            default {};

}
