package com.mercadolindo.entity;

import java.io.Serializable;

import com.mercadolindo.entity.pk.ItemPedidoPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPK id;
	
	@Column(name = "DESCONTO")
	private Double desconto;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	
	@Column(name = "PRECO")
	private Double preco;
	
	
	

}
