package com.mercadolindo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "ID_PAGAMENTO")
@Table(name = "TB_CARTAO_CREDITO")
public class CartaoCreditoEntity extends PagamentoEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "PARCELAS", nullable = false)
	private int parcelas;

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

}
