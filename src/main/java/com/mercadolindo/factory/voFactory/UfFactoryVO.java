package com.mercadolindo.factory.voFactory;

import com.mercadolindo.entity.EstadoEntity;
import com.mercadolindo.model.UfVO;

public class UfFactoryVO {

	public static UfVO converterParaVo(EstadoEntity estado) {
		
		if(estado != null) {
			
			UfVO vo = new UfVO();
			
			vo.setId(estado.getId());
			vo.setNome(estado.getNome());
			
			return vo;
		}
		
		return null;
	}

}
