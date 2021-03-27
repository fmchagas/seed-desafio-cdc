package br.com.fmchagas.desafiocdc.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrosOutPut {
	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutPut> fieldErrors = new ArrayList<>();
	
	public void addError(String errorMessage) {
		globalErrorMessages.add(errorMessage);
	}
	
	public void addFieldError(String field, String message) {
		FieldErrorOutPut fieldError = new FieldErrorOutPut(field, message);
		fieldErrors.add(fieldError);
	}
	
	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}
	
	public List<FieldErrorOutPut> getFieldErrors() {
		return fieldErrors;
	}
}
