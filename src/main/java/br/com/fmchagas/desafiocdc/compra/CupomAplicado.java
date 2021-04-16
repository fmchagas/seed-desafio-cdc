package br.com.fmchagas.desafiocdc.compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fmchagas.desafiocdc.cupom_desconto.CupomDesconto;

@Embeddable
public class CupomAplicado{
	
	@ManyToOne
	private CupomDesconto cupomDesconto;
	@Positive @NotNull
	private BigDecimal percentualDescontoNoMomento;
	@NotNull @Future
	private LocalDateTime validadeMomento;
	
	/**
	 * @Deprecated Usado apenas pelo hibernate
	 */
	@Deprecated
	public CupomAplicado() {}
	
	public CupomAplicado(CupomDesconto cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
		percentualDescontoNoMomento = cupomDesconto.getDescontoEmPorcentagem();
		validadeMomento = cupomDesconto.getValidade();
	}
	
	public BigDecimal getPercentualDescontoNoMomento() {
		return percentualDescontoNoMomento;
	}
	
	public CupomDesconto getCupomDesconto() {
		return cupomDesconto;
	}

}
