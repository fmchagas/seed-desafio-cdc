package br.com.fmchagas.desafiocdc.exception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ValidationErrorHandler {
	@Autowired MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationErrosOutPut handleValidationError(MethodArgumentNotValidException exception) {
		List<ObjectError> globalErros = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		return buildValidationErros(globalErros, fieldErrors);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ValidationErrosOutPut handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		//List<ObjectError> globalErros = List.of(ex.getMessage());
		//List<FieldError> fieldErrors = List.of()
		System.out.println(">>>>>>>>> aheeeeeeeeeeee - handles se vira em tratar e devolver");
		return null;
	}
	

	private ValidationErrosOutPut buildValidationErros(List<ObjectError> globalErros, 
			List<FieldError> fieldErrors) {
		
		ValidationErrosOutPut validationErros = new ValidationErrosOutPut();
		
		globalErros.forEach(error -> validationErros.addError(getErrorMessage(error)));
		
		fieldErrors.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErros.addFieldError(error.getField(), errorMessage);
		});
		
		return validationErros;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
