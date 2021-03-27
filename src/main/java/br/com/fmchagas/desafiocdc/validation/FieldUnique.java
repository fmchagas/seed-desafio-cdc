package br.com.fmchagas.desafiocdc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FieldUniqueValidator.class)
public @interface FieldUnique {
	String message() default "should be unique";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
}
