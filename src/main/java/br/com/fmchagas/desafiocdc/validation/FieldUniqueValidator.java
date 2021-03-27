package br.com.fmchagas.desafiocdc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fmchagas.desafiocdc.categoria.CategoriaRepository;

public class FieldUniqueValidator implements ConstraintValidator<FieldUnique, String>{
	@Autowired CategoriaRepository categoriaRepository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean existeCategoria = categoriaRepository.existsByNome(value);
		
		return !existeCategoria;
	}

}
