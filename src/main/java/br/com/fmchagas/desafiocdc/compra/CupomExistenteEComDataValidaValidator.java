package br.com.fmchagas.desafiocdc.compra;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.fmchagas.desafiocdc.cupom_desconto.CupomDesconto;

@Component
//Total carga:5
public class CupomExistenteEComDataValidaValidator implements Validator {
	
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
		
		//1
		if(request.temCupomDesconto()) {
			Query query = manager.createQuery("select c from CupomDesconto c where c.codigo = :codigo");
			query.setParameter("codigo", request.getCupomDesconto());
			
			//1
			@SuppressWarnings("unchecked")
			List<CupomDesconto> cupons = query.getResultList();
			
			Assert.state(cupons.size() <= 1, ()-> "encontramos mais de um " + cupons.get(0).getClass().getSimpleName()  + " com o atributo codigo = " + cupons.get(0).getCodigo());

			//1
			if(cupons.isEmpty()  || cupons.get(0).invalido()) {
				errors.reject("cupomDesconto", null, "cupom de desconto precisa ser valido");
			}
		}
		
	}

}
