package br.com.fmchagas.desafiocdc.autor;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/autores",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
//Carga total: 3
public final class AutorController {
	//1
	private AutorUseCase autorUseCase;

	public AutorController(AutorUseCase autorUseCase) {
		this.autorUseCase = autorUseCase;
	}
	

	@PostMapping
	//1
	public ResponseEntity<?> novo(@Valid @RequestBody AutorRequestImpl request) {
		//1
		ResponseRequest autorResposne = new ResponseRequest(autorUseCase.execute(request));
		
		return new ResponseEntity<>(autorResposne, HttpStatus.CREATED);
	}

}
