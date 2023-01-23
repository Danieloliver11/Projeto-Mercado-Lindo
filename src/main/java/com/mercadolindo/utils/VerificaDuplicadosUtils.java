package com.mercadolindo.utils;

import java.util.Collections;
import java.util.List;

import com.mercadolindo.exception.ParametroInvalidoException;

public class VerificaDuplicadosUtils {
	
	
	
	@SuppressWarnings("unchecked")
	public static void verificaDuplicado(List lista) {

		lista.forEach(x -> {

			int duplicados = Collections.frequency(lista, x);
			if (duplicados > 1) {
				throw new ParametroInvalidoException("Não é possível cadastrar capacidades producao duplicadas!");
			}
		});
	}

}
