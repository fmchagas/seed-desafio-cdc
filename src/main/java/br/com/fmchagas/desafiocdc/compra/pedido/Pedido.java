package br.com.fmchagas.desafiocdc.compra.pedido;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.compra.Compra;

@Entity
//Total carga:5
public class Pedido {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Long id;
	@OneToOne
	private @NotNull @Valid Compra compra; //1
	
	@ElementCollection
	private @Size(min = 1) Set<ItemPedido> itens = new HashSet<>(); //1
	
	@Deprecated
	public Pedido() {}
	
	public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), "todo pedido deve ter pelo menos um item");
		
		this.compra = compra;
		this.itens.addAll(itens);
	}
	
	//3
	public boolean totalIgual(@Positive @NotNull BigDecimal total) {
		BigDecimal totalCalcluladoPedido = itens.stream().map(ItemPedido::total)
		.reduce(BigDecimal.ZERO, (atual, proximo)->atual.add(proximo));

		return totalCalcluladoPedido.compareTo(total) == 0;
	}

	@Override
	public String toString() {
		return "Pedido [itens=" + itens + "]";
	}
}
