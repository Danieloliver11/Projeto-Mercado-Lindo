package com.mercadolindo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

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
