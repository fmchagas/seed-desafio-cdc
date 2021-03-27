package br.com.fmchagas.desafiocdc.exception;

public class FieldErrorOutPut {

	private String field;
	private String message;
	
	public FieldErrorOutPut() {}
	
	public FieldErrorOutPut(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	public String getField() {
		return field;
	}
	
	public String getMessage() {
		return message;
	}
}
