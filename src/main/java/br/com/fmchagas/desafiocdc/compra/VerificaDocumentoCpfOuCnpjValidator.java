package br.com.fmchagas.desafiocdc.compra;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
//Total carga:3
public class VerificaDocumentoCpfOuCnpjValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//1
		if(errors.hasErrors()) return;
		
		//1
		NovaCompraRequest request = (NovaCompraRequest) target;
		
		//1
		if(!request.documentoValido()) {
			errors.reject("documento", null, "documento precisa ser CPF ou CNPJ");
		}
		
	}

}
