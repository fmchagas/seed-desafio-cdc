package br.com.fmchagas.desafiocdc.categoria;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.fmchagas.desafiocdc.validation.UniqueValue;
//1
public class CategoriaRequest {
	
	@NotEmpty @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES) //corrige B.o do JSON parse error: Cannot construct instance...
	public CategoriaRequest(@NotEmpty String nome) {
		this.nome = nome;
	}
	
	//1
	public Categoria toModel() {
		return new Categoria(nome);
	}
	
	public String getNome() {
		return nome;
	}
}
