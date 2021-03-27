package br.com.fmchagas.desafiocdc.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 64, nullable = false)
	private @NotEmpty String nome;
	
	@Deprecated
	public Categoria(){}
	
	public Categoria(@NotEmpty String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Categoria {nome:" + nome + "}";
	}
	
	public String getNome() {
		return nome;
	}
}
