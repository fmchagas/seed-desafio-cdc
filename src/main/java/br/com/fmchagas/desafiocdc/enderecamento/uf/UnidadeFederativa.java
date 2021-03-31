package br.com.fmchagas.desafiocdc.enderecamento.uf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;

@Entity(name = "uf")
//Total: 1
public class UnidadeFederativa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String nome;
	
	//1
	@ManyToOne
	private @NotNull Pais pais;
	
	@Deprecated
	UnidadeFederativa(){}
	
	public UnidadeFederativa(@NotBlank String nome, @NotNull Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Pais getPais() {
		return pais;
	}

	public boolean pertenceAPais(Pais pais) {
		return this.pais.equals(pais);
	}
}
