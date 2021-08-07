package br.com.fmchagas.desafiocdc.cupom_desconto;

import java.math.BigDecimal;
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

import org.springframework.util.Assert;


@Entity
public class CupomDesconto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String codigo;
	@Column(name = "desc_porcentagem")
	private @NotNull @Positive @Max(100) BigDecimal descontoEmPorcentagem;

	@Column(name = "valido_ate")
	private @NotNull @Future LocalDateTime validade;
	
	/**
	 * @Deprecated Usado apenas pelo hibernate
	 */
	@Deprecated
	public CupomDesconto() {}
	
	public CupomDesconto(@NotBlank String codigo, 
			@NotNull @Positive @Max(100) BigDecimal descontoEmPorcentagem,
			@NotNull @Future LocalDateTime validade) {
			Assert.isTrue(validade.compareTo(LocalDateTime.now()) >= 0, "A validade tem que ser no futuro");
			
			this.codigo = codigo;
			this.descontoEmPorcentagem = descontoEmPorcentagem;
			this.validade = validade;
	}
	
	public boolean valido() {
		return LocalDateTime.now().compareTo(validade) <= 0;
	}
	
	public boolean invalido() {
		return !valido();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public BigDecimal getDescontoEmPorcentagem() {
		return descontoEmPorcentagem;
	}
	
	public LocalDateTime getValidade() {
		return validade;
	}
}
