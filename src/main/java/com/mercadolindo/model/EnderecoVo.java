package com.mercadolindo.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadolindo.enums.SimNaoEnum;

@JsonIgnoreProperties(value= "nomeCidade", allowSetters = false, allowGetters  = true)
public class EnderecoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
	
	private String bairro;
	
	private String nomeRua;
	
	private String numeroCasa;
	
	private String cep;
	
	private Long idMunicipio;
	
	private SimNaoEnum enderecoPrincipal;
	
	private String nomeCidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public SimNaoEnum getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setEnderecoPrincipal(SimNaoEnum enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}
	
	

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((numeroCasa == null) ? 0 : numeroCasa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoVo other = (EnderecoVo) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (numeroCasa == null) {
			if (other.numeroCasa != null)
				return false;
		} else if (!numeroCasa.equals(other.numeroCasa))
			return false;
		return true;
	}


}
