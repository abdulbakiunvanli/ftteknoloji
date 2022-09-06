package com.abdulbakiunvanli.ftteknoloji.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnyNotNullValidator implements ConstraintValidator<AnyNotNull, Object> {

	public boolean isValid(Object value, ConstraintValidatorContext cxt) {

		return value == null ? false : true;
	}
}
