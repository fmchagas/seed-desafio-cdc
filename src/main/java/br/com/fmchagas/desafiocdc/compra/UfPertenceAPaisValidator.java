package br.com.fmchagas.desafiocdc.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa;

@Component
//Total carga: 6
public class UfPertenceAPaisValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//1
		if (errors.hasErrors()) return;
		
		//1
		NovaCompraRequest request = (NovaCompraRequest) target;
		
		//1
		if (request.temUf()) {
			//1
			Pais pais = manager.find(Pais.class, request.getPaisId());
			
			//1
			UnidadeFederativa uf = manager.find(UnidadeFederativa.class, request.getUfId());
			
			//1
			if (!uf.pertenceAPais(pais)) {
				errors.reject("ufId", null, "está unidade federativa não pertencer ao país selecionado!");
			}
		}
	}
}
