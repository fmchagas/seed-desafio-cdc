package br.com.fmchagas.desafiocdc.cupom_desconto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fmchagas.desafiocdc.validation.UniqueValue;

public class NovoCupomDescontoRequest {
	
	@NotBlank @UniqueValue(domainClass = CupomDesconto.class, fieldName = "codigo")
	private String codigo;
	
	@NotNull @Positive @Max(value = 100)
	private BigDecimal descontoEmPorcentagem;
	
	@NotNull @Future
	private LocalDateTime validade;

	public NovoCupomDescontoRequest(@NotBlank String codigo, @NotNull @Positive @Max(100) BigDecimal descontoEmPorcentagem,
			@NotNull @Future LocalDateTime validade) {
		this.codigo = codigo;
		this.descontoEmPorcentagem = descontoEmPorcentagem;
		this.validade = validade;
	}

	public CupomDesconto toModel() {
		return new CupomDesconto(codigo, descontoEmPorcentagem, validade);
	}
}
