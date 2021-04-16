package br.com.fmchagas.desafiocdc.compra;

import java.math.BigDecimal;

public class CompraResponse {

	private String nome;
	private String documento;
	private String sobrenome;
	private String email;
	private String telefone;
	private String endereco;
	private String complemento;
	private String cep;
	private String pais;
	private String uf;
	private BigDecimal totalPedido;
	private boolean cupomDesconto;
	private BigDecimal totalComDesconto;
	private BigDecimal percentualDesconto;

	public CompraResponse(Compra compra) {
		nome = compra.getNome();
		documento = compra.getDocumento();
		sobrenome = compra.getSobrenome();
		email = compra.getEmail();
		telefone = compra.getTelefone();
		endereco = compra.getEndereco();
		complemento = compra.getComplemento();
		cep = compra.getCep();
		uf = compra.getUf().getNome();
		pais = compra.getPais().getNome();
		totalPedido = compra.getPedido().totalCalcluladoPedido();
		
		cupomDesconto = compra.isCupomAplicado();
		if(compra.isCupomAplicado())
			percentualDesconto = compra.getCupomAplicado().getPercentualDescontoNoMomento();
	}

	public String getNome() {
		return nome + " " + sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco + ", " + cep + ", " + pais +" - " + uf;
	}

	public String getComplemento() {
		return complemento;
	}
	
	public BigDecimal getTotalPedido() {
		return totalPedido;
	}
	
	public boolean getCupomDesconto() {
		return cupomDesconto;
	}
	
	public BigDecimal getTotalComDesconto() {
		if(cupomDesconto)
			totalComDesconto = totalPedido.subtract(totalPedido.multiply(percentualDesconto).divide(new BigDecimal(100)));
		
		return totalComDesconto;
	}
}
