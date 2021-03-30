package br.com.fmchagas.desafiocdc.enderecamento.pais;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/pais")
//Carga total: 3
public class PaisController {
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	//1
	public ResponseEntity<PaisResposne> novo(@Valid @RequestBody PaisRequest request, UriComponentsBuilder uriBuilder) {
		//1
		final Pais paisSalvo = manager.merge(request.toModel());
		//1
		final PaisResposne paisResponse = new PaisResposne(paisSalvo);
		
		URI uri = uriBuilder.path("/api/v1/pais/{id}").buildAndExpand(paisResponse.getId()).toUri();
		
		return ResponseEntity.created(uri).body(paisResponse);
	}
}
