package com.mercadolindo.model;

import java.io.Serializable;

public class AtualizarSenhaVO implements  Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String senhaAntiga;
	
	private String senhaNova;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
	
	

}
