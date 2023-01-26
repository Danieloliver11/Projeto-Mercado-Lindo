package com.mercadolindo.factory.entityFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mercadolindo.entity.EnderecoEntity;
import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.model.EnderecoVo;

@Component
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
		entity.setFlagEnderecoPrincipal(enderecos.getEnderecoPrincipal());
		entity.setNumero(enderecos.getNumeroCasa());
		entity.setPessoa(pessoaEntity);
		entity.setRua(enderecos.getNomeRua());

		return entity;

	}

	public static List<EnderecoEntity> converterParaAtualizarListaEntity(List<EnderecoEntity> enderecosBanco,
			List<EnderecoVo> enderecosAtualizado) {
		
		for (EnderecoEntity enderecoEntity : enderecosBanco) {
			
			for(EnderecoVo enderecoVO : enderecosAtualizado) {
				
				if(enderecoEntity.getId().equals(enderecoVO.getId())){
					
					enderecoEntity.setBairro(enderecoVO.getBairro());
					enderecoEntity.setCep(enderecoVO.getCep());
					enderecoEntity.setFlagEnderecoPrincipal(enderecoVO.getEnderecoPrincipal());
					enderecoEntity.setNumero(enderecoVO.getNumeroCasa());
					enderecoEntity.setRua(enderecoVO.getNomeRua());
				}
					
				
			}
				
			
		}
		
		return enderecosBanco;
	}
	



}
