package br.com.fmchagas.desafiocdc.compra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
//Total: 3
public class PaisTemUfValidator implements Validator{
	@PersistenceContext
	private EntityManager manager;
	
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
		
		Query query = manager.createQuery("select 1 from br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa where pais_id = :id");
		query.setParameter("id", request.getPaisId());
		
		@SuppressWarnings("unchecked")
		List<Integer> lista = (List<Integer>) query.getResultList();
		
		//1
		if(!request.temUf() & !lista.isEmpty())
			errors.reject("ufId", null, "este país tem unidade federativa. Selecione uma!");
	}

}
