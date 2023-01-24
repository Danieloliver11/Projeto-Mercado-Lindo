package com.mercadolindo.factory.entityFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolindo.entity.EnderecoEntity;
import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.model.EnderecoVo;

public class EnderecoEntityFactory {

	public static List<EnderecoEntity> converterListParaEntity(List<EnderecoVo> enderecos, PessoaEntity pessoaEntity) {

		if (!enderecos.isEmpty()) {

			return enderecos.stream().map(endereco -> converterParaEntity(endereco,pessoaEntity)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public static EnderecoEntity converterParaEntity(EnderecoVo enderecos, PessoaEntity pessoaEntity) {

		EnderecoEntity entity = new EnderecoEntity();
		entity.setBairro(enderecos.getBairro());
		entity.setCep(enderecos.getCep());
//		entity.setCidade(enderecos.get);
		entity.setFlagEnderecoPrincipal(enderecos.getEnderecoPrincipal());
		entity.setNumero(enderecos.getNumeroCasa());
		entity.setPessoa(pessoaEntity);
		entity.setRua(enderecos.getNomeRua());

		return entity;

	}

}
