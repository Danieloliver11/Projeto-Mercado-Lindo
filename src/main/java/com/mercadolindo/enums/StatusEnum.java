package com.mercadolindo.enums;

public enum StatusEnum {
	
	PENDENTE(1,"Pendente"),
	PAGO(2,"Pago"),
	CANCELADO(3,"Cancelado");
	
	Integer inteiro;
	String string;

	StatusEnum(Integer inteiro, String string) {
		
		this.inteiro = inteiro;
		this.string = string;
	}
	
	public static StatusEnum getById(int inteiro) {
		
		for(StatusEnum e : values() ) {
			if(e.inteiro.equals(inteiro)) {
				return e;
			}
		}
		return null;
	}

}
