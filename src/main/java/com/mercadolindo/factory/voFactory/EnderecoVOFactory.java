package com.mercadolindo.factory.voFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolindo.entity.EnderecoEntity;
import com.mercadolindo.model.EnderecoVo;

public class EnderecoVOFactory {

	public static List<EnderecoVo> converteListaParaVO(List<EnderecoEntity> enderecos) {
		
		if(!enderecos.isEmpty()) {
			return enderecos.stream().map(EnderecoVOFactory::converteParaVO).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	public static EnderecoVo converteParaVO(EnderecoEntity endereco) {
		
		EnderecoVo vo = new EnderecoVo();	
		vo.setBairro(endereco.getBairro());
		vo.setCep(endereco.getCep());
		vo.setEnderecoPrincipal(endereco.getFlagEnderecoPrincipal());
		vo.setId(endereco.getId());
		vo.setNomeRua(endereco.getRua());
		vo.setNumeroCasa(endereco.getNumero());
		vo.setMunicipio(CidadeFactoryVO.converterParaVo(endereco.getCidade()));
		
		return vo;
	}

}
