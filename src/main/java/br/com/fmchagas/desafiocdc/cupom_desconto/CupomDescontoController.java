package br.com.fmchagas.desafiocdc.cupom_desconto;

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

@RestController
@RequestMapping("/api/v1/cupons")
public class CupomDescontoController {
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CupomDescontoResponse> novo(@Valid @RequestBody NovoCupomDescontoRequest request) {
		
		final CupomDesconto cupomDesconto = manager.merge(request.toModel());
		
		final CupomDescontoResponse cdr = new CupomDescontoResponse(cupomDesconto);
		
		URI uri = URI.create("/api/v1/cupons/"+cupomDesconto.getId());
		
		return ResponseEntity.created(uri).body(cdr);
	}
}
