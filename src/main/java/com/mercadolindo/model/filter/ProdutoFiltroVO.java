package com.mercadolindo.model.filter;

import java.math.BigDecimal;

public class ProdutoFiltroVO {

	private String nome;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private boolean freteGratis;
	private Long idUF;
	private Long idCategoria;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	public boolean isFreteGratis() {
		return freteGratis;
	}
	public void setFreteGratis(boolean freteGratis) {
		this.freteGratis = freteGratis;
	}
	public Long getIdUF() {
		return idUF;
	}
	public void setIdUF(Long idUF) {
		this.idUF = idUF;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public boolean incluiNome() {
		return nome != null && !nome.isBlank();
	}
	
	public boolean incluiValorMinimo() {
		return valorMinimo != null;
	}
	
	public boolean incluiValorMaximo() {
		return valorMaximo != null;
	}
	
	public boolean incluiIdCategoria() {
		return idCategoria != null;
	}
	
	public boolean isFiltrosInexistentes(){
		return !incluiNome() && 
			   !incluiValorMinimo() &&
			   !incluiValorMaximo() && 
			   !incluiIdCategoria();
	}
	
}
