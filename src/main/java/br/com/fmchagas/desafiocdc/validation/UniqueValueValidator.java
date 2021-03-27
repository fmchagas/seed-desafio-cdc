package br.com.fmchagas.desafiocdc.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{
	private String domainAttribute;
	private Class<?> clazz;
	@PersistenceContext private EntityManager manager;


	@Override
	public void initialize(UniqueValue params) {
		domainAttribute = params.fieldName();
		clazz = params.domainClass();
	}
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from " + clazz.getName() +
				 " c where c." + domainAttribute + "=:value");
		query.setParameter("value", value);
		
		List<?> list = (List<?>)query.getResultList();
		
		Assert.state(list.size() <= 1, "encontramos mais de um " + clazz.getName() + " com o atributo " + domainAttribute + " = " + value + ", quantidade encontrada: " + list.size());		 
		
		return list.isEmpty();
	}

}
