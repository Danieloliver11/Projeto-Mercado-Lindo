package com.mercadolindo.model;

import javax.validation.constraints.NotNull;

public class CategoriaVO {

	@NotNull(message = "O id categoria é obrigatório!")
	private Long id;
	
	private String nome;

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

	

}
