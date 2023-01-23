package com.mercadolindo.model;

import java.io.Serializable;

import com.mercadolindo.enums.SimNaoEnum;

public class EnderecoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String bairro;
	
	private String nomeRua;
	
	private Integer numeroCasa;
	
	private String cep;
	
	private Long idMunicipio;
	
	private SimNaoEnum simNaoEnum;

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

	public Integer getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(Integer numeroCasa) {
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

	public SimNaoEnum getSimNaoEnum() {
		return simNaoEnum;
	}

	public void setSimNaoEnum(SimNaoEnum simNaoEnum) {
		this.simNaoEnum = simNaoEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (numeroCasa == null) {
			if (other.numeroCasa != null)
				return false;
		} else if (!numeroCasa.equals(other.numeroCasa))
			return false;
		return true;
	}
	
	
	
	
	
	


}
