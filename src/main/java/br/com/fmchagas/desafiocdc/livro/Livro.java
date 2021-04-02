package br.com.fmchagas.desafiocdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fmchagas.desafiocdc.autor.Autor;
import br.com.fmchagas.desafiocdc.categoria.Categoria;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private @NotEmpty String titulo;
	private @NotEmpty @Size(max = 500) String resumo;
	private String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	
	@Column(name = "numero_pagina")
	private @NotNull @Min(100) Integer numeroPagina;
	private @NotEmpty String isbn;
	
	@Column(name = "data_publicacao")
	@Future
	private @NotNull LocalDate dataPublicacao;
	
	@ManyToOne @Valid
	private Autor autor;
	
	@ManyToOne @Valid
	private Categoria categoria;
	
	@Deprecated
	public Livro(){}
	
	public Livro(@NotEmpty String titulo, @NotEmpty @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPagina, @NotEmpty String isbn,
			@NotNull @Future LocalDate dataPublicacao, @NotNull @Valid Autor autor, @NotNull @Valid Categoria categoria) {
				this.titulo = titulo;
				this.resumo = resumo;
				this.sumario = sumario;
				this.preco = preco;
				this.numeroPagina = numeroPagina;
				this.isbn = isbn;
				this.dataPublicacao = dataPublicacao;
				this.autor = autor;
				this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Livro {titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco=" + preco
				+ ", numeroPagina=" + numeroPagina + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao
				+ ", autor=" + autor + ", categoria=" + categoria + "}";
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Autor getAutor() {
		return autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}
}
