package com.mercadolindo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProdutoVO {

	private Long id;
	
	@Size(max = 150 , message = "O campo nome deve obter no máximo {max} caracteres")
	@NotBlank(message = "O campo nome é obrigatório!")
	private String nome;
	
    @DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10000000 , fraction = 10 ,
			message = "O preço deve obter no máximo {integer} de inteiros e máximo {fraction} pós vírgula!")
	@NotNull(message = "O campo preço é obrigatório!")
	private BigDecimal preco;

	@Positive(message = "O campo quantidade não pode ser negativo ou zgetero")
	@NotNull(message = "O campo quantidade é obrigatório!")
	private Integer quantidade;

	@Size(max = 150 , message = "O campo descricao deve obter no máximo {max} caracteres")
	@NotBlank(message = "O campo descricao é obrigatório!")
	private String descricao;
	
	@Valid
	@NotEmpty
	private List<CategoriaVO> categorias;
	
	private boolean freteGratis;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isFreteGratis() {
		return freteGratis;
	}
	
	public void setFreteGratis(boolean freteGratis) {
		this.freteGratis = freteGratis;
	}
	
	public List<CategoriaVO> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<CategoriaVO> categorias) {
		this.categorias = categorias;
	}
	
}
