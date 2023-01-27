package com.mercadolindo.factory.voFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolindo.entity.CidadeEntity;
import com.mercadolindo.model.MunicipioVO;

public class CidadeFactoryVO {

	public static List<MunicipioVO>  converterListaParaVo(List<CidadeEntity> cidadeListEntity) {
		
		if(!cidadeListEntity.isEmpty()) {
			
			return cidadeListEntity.stream().map(CidadeFactoryVO::converterParaVo).collect(Collectors.toList());
		}
		
		return Collections.emptyList();
	}
	
	public static MunicipioVO converterParaVo(CidadeEntity cidadeEntity) {
		
		MunicipioVO vo = new MunicipioVO();
		
		vo.setId(cidadeEntity.getId());
		vo.setNome(cidadeEntity.getNome());
		vo.setEstado(UfFactoryVO.converterParaVo(cidadeEntity.getEstado()));
		
		return vo;
		
	}

}
