package com.mercadolindo.factory.entityFactory;

import com.mercadolindo.entity.UsuarioEntity;
import com.mercadolindo.model.PessoaCadastroVO;

public class UsuarioFactory {

	public static UsuarioEntity converterParaEntity(PessoaCadastroVO pessoaVO) {
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setLogin(pessoaVO.getEmail());
		usuarioEntity.setNomeUsuario(pessoaVO.getNomeUsuario());
		usuarioEntity.setSenha(pessoaVO.getSenha());
		
		
		return usuarioEntity;
	}

}
