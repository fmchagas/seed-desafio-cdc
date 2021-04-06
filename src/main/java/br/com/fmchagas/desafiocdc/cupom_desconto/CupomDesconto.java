package br.com.fmchagas.desafiocdc.cupom_desconto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class CupomDesconto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String codigo;
	@Column(name = "desc_porcentagem")
	private @NotNull @Positive @Max(100) Integer descontoEmPorcentagem;
	@Column(name = "valido_ate")
	private @NotNull @Future LocalDateTime validade;
	
	@Deprecated
	public CupomDesconto() {}
	
	public CupomDesconto(@NotBlank String codigo, @NotNull @Positive @Max(100) Integer descontoEmPorcentagem,
			@NotNull @Future LocalDateTime validade) {
				this.codigo = codigo;
				this.descontoEmPorcentagem = descontoEmPorcentagem;
				this.validade = validade;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public Integer getDescontoEmPorcentagem() {
		return descontoEmPorcentagem;
	}
	
	public LocalDateTime getValidade() {
		return validade;
	}
}
