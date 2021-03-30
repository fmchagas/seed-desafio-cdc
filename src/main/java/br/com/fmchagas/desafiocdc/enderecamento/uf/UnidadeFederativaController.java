package br.com.fmchagas.desafiocdc.enderecamento.uf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ufs")
//Total: 3
public class UnidadeFederativaController {
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	//1
	public ResponseEntity<UnidadeFederativaRespone> novo(@Valid @RequestBody UnidadeFederativaRequest request) {
		//1
		final UnidadeFederativa ufSalva = manager.merge(request.toModel(manager));
		//1
		final UnidadeFederativaRespone ufResponse = new UnidadeFederativaRespone(ufSalva);
		
		return new ResponseEntity<>(ufResponse, HttpStatus.CREATED);
	}
}
