package br.com.fmchagas.desafiocdc.autor;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
//Total: 4
public class AutorComEmailDuplicadoValidator implements Validator {
	//1
	private AutorRepository autorRepository;

	public AutorComEmailDuplicadoValidator(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		//1
		return AutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//1
		if (errors.hasErrors())
			return;

		AutorRequest request = (AutorRequest) target;
		Optional<Autor> podeTerAutor = autorRepository.findByEmail(request.getEmail());
		
		//1
		if (podeTerAutor.isPresent()) {
			errors.rejectValue("email", null, "Existe um(a) autor(a) com mesmo e-mail cadastrado(a)!");
		}
	}

}
