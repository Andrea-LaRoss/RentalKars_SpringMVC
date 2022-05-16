package com.rentalkarsspringmvc.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class Before2DaysValidator implements ConstraintValidator<Before2Days, LocalDate> {
    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;

        Period timeDiff = startDate.until(LocalDate.now());
        if(timeDiff.getYears() == 0 && timeDiff.getMonths() == 0 && timeDiff.getDays() < 2) {
            valid = true;
        }

        return valid;
    }
}
