package com.abdulbakiunvanli.ftteknoloji.annotations;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateNotNullValidator implements ConstraintValidator<DateNotNull, LocalDate> {

	public boolean isValid(LocalDate date, ConstraintValidatorContext cxt) {

		return date == null ? false : true;
	}
}
