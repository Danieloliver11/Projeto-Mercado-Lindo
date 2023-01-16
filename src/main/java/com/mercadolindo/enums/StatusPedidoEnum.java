package com.mercadolindo.enums;

public enum StatusPedidoEnum {
	
	PENDENTE(1,"Pendente"),
	PAGO(2,"Pago"),
	CANCELADO(3,"Cancelado");
	
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
