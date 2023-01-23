package com.mercadolindo.enums;

public enum SimNaoEnum {

	S, N;

	public static Boolean toBoolean(SimNaoEnum valor) {
		if (valor != null)
			return SimNaoEnum.S.equals(valor);
		else
			return null;
	}

	public static SimNaoEnum toSimNao(Boolean valor) {
		if (valor != null)
			return valor ? SimNaoEnum.S : SimNaoEnum.N;
		else
			return null;
	}

}
