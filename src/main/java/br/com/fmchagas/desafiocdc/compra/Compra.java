package br.com.fmchagas.desafiocdc.compra;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.compra.pedido.Pedido;
import br.com.fmchagas.desafiocdc.cupom_desconto.CupomDesconto;
import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa;

//Total carga: 3
@Entity
public class Compra {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Long id;
	
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	private @Embedded CupomAplicado cupomAplicado;
	
	@ManyToOne //1
	private @NotNull Pais pais;
	
	@ManyToOne //1
	private UnidadeFederativa uf;
	
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST) //1
	private Pedido pedido;
	
	@Deprecated
	public Compra() {}

	public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String telefone, @NotBlank String cep, @NotNull Pais pais, Function<Compra, Pedido> funcaoCriaPedido) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.telefone = telefone;
				this.cep = cep;
				this.pais = pais;
				this.pedido = funcaoCriaPedido.apply(this);
	}
	
	public void setUf(@NotNull @Valid UnidadeFederativa uf) {
		Assert.notNull(pais, "ops, não podemos associar uma unidade federativa enquanto o país for nulo");
		Assert.isTrue(uf.pertenceAPais(pais), "Está unidade federativa não pertencer ao país selecionado");
		
		this.uf = uf;
	}
	
	public void setCupomDesconto(CupomDesconto cupomDesconto) {
		Assert.isTrue(cupomDesconto.valido(), "Ops, o cupom de desconto aplicado não está valido");
		Assert.isNull(cupomAplicado, "Ops, não podemos associar um cupom de desconto enquanto nulo");
		
		this.cupomAplicado = new CupomAplicado(cupomDesconto);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", telefone=" + telefone
				+ ", cep=" + cep + ", pais=" + pais + ", uf=" + uf + ", pedido=" + pedido + "]";
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public CupomAplicado getCupomAplicado() {
		return cupomAplicado;
	}
	
	public boolean isCupomAplicado() {
		return getCupomAplicado() != null;
	}

	public Pais getPais() {
		return pais;
	}

	public UnidadeFederativa getUf() {
		return uf;
	}

	public Pedido getPedido() {
		return pedido;
	}
}
