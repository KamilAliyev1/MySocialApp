package com.kmsocialapp.myutil.customconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EitherOrValidator implements ConstraintValidator<EitherOr,EitherorObject> {

    @Override
    public boolean isValid(final EitherorObject comment, final ConstraintValidatorContext constraintValidatorContext) {

        return  ((comment.getFirstforEitheror()==null && comment.getSecondforEeitheror()!=null)
                || (comment.getFirstforEitheror()!=null && comment.getSecondforEeitheror()==null));
    }
}

