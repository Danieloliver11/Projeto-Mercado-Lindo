package com.mercadolindo.factory.entityFactory;


import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.model.PessoaCadastroVO;

public class PessoaCadastroEntityFactory {

	public static PessoaEntity converterParaEntity(PessoaCadastroVO pessoaVO) {
		
		if(pessoaVO != null) {
			
			PessoaEntity pessoaEntity = new PessoaEntity();
			
			pessoaEntity.setNome(pessoaVO.getNome());
			pessoaEntity.setCpf(pessoaVO.getCpf());
			pessoaEntity.setDataNascimento(pessoaVO.getDataNascimento());
			pessoaEntity.setEmail(pessoaVO.getEmail());
			pessoaEntity.setSexo(pessoaVO.getSexo());
			pessoaEntity.setUsuario(UsuarioEntityFactory.converterParaEntity(pessoaVO));
			
			return pessoaEntity;
		}
		return null;
		
	}

	public static PessoaEntity converterParaAtualizarEntity(PessoaEntity pessoaAtualizada,
			 PessoaCadastroVO pessoaVO) {

		pessoaAtualizada.setDataNascimento(pessoaVO.getDataNascimento());
		pessoaAtualizada.setEmail(pessoaVO.getEmail());
//		pessoaAtualizada.setEnderecos(null);
		pessoaAtualizada.setNome(pessoaVO.getNome());
		pessoaAtualizada.setSexo(pessoaVO.getSexo());
		pessoaAtualizada.setUsuario(UsuarioEntityFactory.converterParaAtualizarEntity(pessoaAtualizada,pessoaVO));
		
		return pessoaAtualizada;
	}

}
