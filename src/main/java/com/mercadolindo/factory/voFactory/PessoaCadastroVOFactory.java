package com.mercadolindo.factory.voFactory;

import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.model.PessoaCadastroVO;

public class PessoaCadastroVOFactory {

	public static PessoaCadastroVO converterParaVO(PessoaEntity save) {
		
		PessoaCadastroVO pessoaVo = new PessoaCadastroVO();
		pessoaVo.setCpf(save.getCpf());
		pessoaVo.setDataNascimento(save.getDataNascimento());
		pessoaVo.setEmail(save.getEmail());
		pessoaVo.setEnderecos(EnderecoVOFactory.converteParaVO(save.getEnderecos()));
		pessoaVo.setId(save.getId());
		pessoaVo.setNome(save.getNome());
		pessoaVo.setNomeUsuario(save.getUsuario().getNomeUsuario());
		pessoaVo.setSexo(save.getSexo());
		
		return pessoaVo;
	}

}
