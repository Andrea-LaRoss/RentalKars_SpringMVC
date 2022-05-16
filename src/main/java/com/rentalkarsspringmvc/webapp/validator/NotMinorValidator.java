package com.rentalkarsspringmvc.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.*;

public class NotMinorValidator implements ConstraintValidator<NotMinor, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;

        Period period = birthday.until(LocalDate.now());

        if(period.getYears() > 18) {
            valid = true;
        }

        return valid;
    }

}
