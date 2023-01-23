package com.mercadolindo.enums;

public enum StatusPedidoEnum {
	
	ATIVO(1,"Ativo"),
	BLOQUEADO(2,"Bloqueado"),
	DESATIVADO(3,"Desativado");
	
	Integer inteiro;
	String string;

	StatusPedidoEnum(Integer inteiro, String string) {
		
		this.inteiro = inteiro;
		this.string = string;
	}
	
	public static StatusPedidoEnum getById(int inteiro) {
		
		for(StatusPedidoEnum e : values() ) {
			if(e.inteiro.equals(inteiro)) {
				return e;
			}
		}
		return null;
	}

}
