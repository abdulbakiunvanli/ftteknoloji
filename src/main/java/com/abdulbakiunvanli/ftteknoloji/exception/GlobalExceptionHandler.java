package com.abdulbakiunvanli.ftteknoloji.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public final String handleConstraintViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String message = ex.getMostSpecificCause().getMessage();
		if (message != null) {
			try {
				if (message.startsWith("Duplicate entry")) {
					message = message.replace("_UNIQUE'", "").substring(message.lastIndexOf(".") + 1);
					message = "Girmiş olduğunuz " + message + " sistemde kayıtlıdır.";
				} else {
					message = "Hata oluştu.";
				}
			} catch (Exception e) {
				message = "Hata oluştu.";
			}
		} else {
			message = "Hata oluştu.";
		}

		return message; // JSON halinde gönderilebilir.
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public final String resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		return ex.getMessage(); // JSON halinde gönderilebilir.
	}
}
