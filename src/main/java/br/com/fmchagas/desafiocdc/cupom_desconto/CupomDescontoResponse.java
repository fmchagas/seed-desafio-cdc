package br.com.fmchagas.desafiocdc.cupom_desconto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CupomDescontoResponse {
	
	public CupomDescontoResponse(CupomDesconto cupomDesconto) {
		id = cupomDesconto.getId();
		codigo = cupomDesconto.getCodigo();
		desconto =  cupomDesconto.getDescontoEmPorcentagem();
		validade = cupomDesconto.getValidade();
	}
	
	private Long id;
	private String codigo;
	private Integer desconto;
	private LocalDateTime validade;
	
	public Long getId() {
		return id;
	}
	public String getCodigo() {
		return codigo;
	}
	public Integer getDescontoDe() {
		return desconto;
	}
	public String getValidoAte() {
		return validade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
	}
}
