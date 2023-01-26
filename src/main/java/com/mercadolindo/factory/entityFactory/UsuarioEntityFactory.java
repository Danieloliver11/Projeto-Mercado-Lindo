package com.mercadolindo.factory.entityFactory;

import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.entity.UsuarioEntity;
import com.mercadolindo.model.PessoaCadastroVO;

public class UsuarioEntityFactory {

	public static UsuarioEntity converterParaEntity(PessoaCadastroVO pessoaVO) {
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setLogin(pessoaVO.getEmail());
		usuarioEntity.setNomeUsuario(pessoaVO.getNomeUsuario());
		usuarioEntity.setSenha(pessoaVO.getSenha());
		
		
		return usuarioEntity;
	}

	public static UsuarioEntity converterParaAtualizarEntity(PessoaEntity pessoaEntity, PessoaCadastroVO pessoaVO) {
		
		UsuarioEntity usuarioAtualizado = pessoaEntity.getUsuario();
		
		usuarioAtualizado.setLogin(pessoaEntity.getEmail());
		usuarioAtualizado.setNomeUsuario(pessoaVO.getNomeUsuario());
		
		return usuarioAtualizado;
	}

}
