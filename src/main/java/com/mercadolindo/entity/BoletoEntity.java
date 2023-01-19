package com.mercadolindo.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "ID_PAGAMENTO")
@Table(name = "TB_BOLETO")
public class BoletoEntity extends PagamentoEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "DATA_VENCIMENTO", nullable = false)
	private ZonedDateTime dataVencimento;
	
	@Column(name = "DATA_PAGAMENTO", nullable = false)
	private ZonedDateTime dataPagamento;

	public ZonedDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(ZonedDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public ZonedDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(ZonedDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
