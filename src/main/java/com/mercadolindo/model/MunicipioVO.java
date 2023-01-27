package com.mercadolindo.model;

import java.io.Serializable;

public class MunicipioVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	private UfVO estado;

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

	public UfVO getEstado() {
		return estado;
	}

	public void setEstado(UfVO estado) {
		this.estado = estado;
	}


	
	

}
