package com.mercadolindo.enums;

public enum SexoEnum {
	
	M(1,"Masculino"),
	F(2,"Feminino");

	Integer numero;
	String string;
	
	SexoEnum(Integer i, String string) {
		this.numero = i ;
		this.string =  string;
	}

	public String getString() {
		return string;
	}
	
	public static SexoEnum getById(int id) {
	    for(SexoEnum e : values()) {
	        if(e.numero.equals(id)) 
	        	return e;
	    }
	    return null;
	 }
	
	
	
	
	
	

}
