package br.com.fmchagas.desafiocdc.pedido;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa;

//Total carga: 2
public class Pedido {

	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	//1
	@ManyToOne
	private @NotNull Pais pais;
	//1
	@ManyToOne
	private UnidadeFederativa uf;

	public Pedido(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String telefone, @NotBlank String cep, @NotNull Pais pais) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.telefone = telefone;
				this.cep = cep;
				this.pais = pais;
	}
	
	public void setUf(@NotNull @Valid UnidadeFederativa uf) {
		Assert.notNull(pais, "ops, não podemos associar uma unidade federativa enquanto o país for nulo");
		Assert.isTrue(uf.pertenceAPais(pais), "Está unidade federativa não pertencer ao país selecionado");
		this.uf = uf;
	}
	

	@Override
	public String toString() {
		return "Pedido [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento + ", telefone=" + telefone + ", cep=" + cep
				+ ", pais=" + pais.getNome() + ", uf=" + uf + "]";
	}
}
