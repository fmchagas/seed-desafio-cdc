package br.com.fmchagas.desafiocdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

//Total carga: 1
public class LivroResponse {
	private String titulo;
	private BigDecimal preco;
	private Integer numeroPagina;
	private String isbn;
	private LocalDate dataPublicacao;
	private String categoria;
	private String autor;
	
	//1
	public LivroResponse(Livro livro) {
		titulo = livro.getTitulo();
		preco = livro.getPreco();
		numeroPagina = livro.getNumeroPagina();
		isbn = livro.getIsbn();
		dataPublicacao = livro.getDataPublicacao();
		categoria = livro.getCategoria().getNome();
		autor = livro.getAutor().getNome();
	}

	public String getTitulo() {
		return titulo;
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

	public String getCategoria() {
		return categoria;
	}

	public String getAutor() {
		return autor;
	}
}
