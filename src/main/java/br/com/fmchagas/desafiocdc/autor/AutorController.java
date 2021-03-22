package br.com.fmchagas.desafiocdc.autor;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/autores", 
				consumes = {MediaType.APPLICATION_JSON_VALUE}, 
				produces = MediaType.APPLICATION_JSON_VALUE, 
				method = {RequestMethod.POST})
//Carga total: 2
public class AutorController {
	
	private AutorRepository autorRepository;

	public AutorController(AutorRepository autorRepository){
		this.autorRepository = autorRepository;
	}
	
	@PostMapping
	//1
	public ResponseEntity<AutorForm> novo(@Valid @RequestBody AutorForm request){
		//1
		Autor autor = request.toModel();
		
		autorRepository.save(autor);
		request.setId(autor.getId());
		request.setCriadoEm(autor.getCriadoEm());
		
		return new ResponseEntity<>(request, HttpStatus.CREATED);
	}
	
}
