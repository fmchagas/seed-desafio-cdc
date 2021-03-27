package br.com.fmchagas.desafiocdc.autor;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/autores",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
//Carga total: 3
public final class AutorController {
	//1
	private AutorRepository autorRepository;
	//1
	//private AutorComEmailDuplicadoValidator autorComEmailDuplicadoValidator;

	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
		//this.autorComEmailDuplicadoValidator = autorComEmailDuplicadoValidator;
	}
	
	
	//@InitBinder
	/*
	 * public void init(WebDataBinder binder) {
	 * binder.addValidators(autorComEmailDuplicadoValidator); }
	 */

	@PostMapping
	//1
	public ResponseEntity<?> novo(@Valid @RequestBody AutorForm request) {
		//1
		Autor autor = request.toModel();

		autor = autorRepository.save(autor);

		return new ResponseEntity<>(autor.toString(), HttpStatus.CREATED);
	}

}
