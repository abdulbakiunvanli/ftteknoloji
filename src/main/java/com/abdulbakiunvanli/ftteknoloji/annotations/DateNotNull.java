package com.abdulbakiunvanli.ftteknoloji.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateNotNullValidator.class)
public @interface DateNotNull {
	
	public String message();

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}