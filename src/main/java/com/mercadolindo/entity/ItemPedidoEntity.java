package com.mercadolindo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mercadolindo.entity.pk.ItemPedidoPK;

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
