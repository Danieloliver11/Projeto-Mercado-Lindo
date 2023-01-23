package com.mercadolindo.factory.entityFactory;

import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.model.PessoaCadastroVO;

public class PessoaCadastroFactory {

	public static PessoaEntity converterParaEntity(PessoaCadastroVO pessoaVO) {
		
		if(pessoaVO != null) {
			
			PessoaEntity pessoaEntity = new PessoaEntity();
			
			pessoaEntity.setNome(pessoaVO.getNome());
			pessoaEntity.setCpf(pessoaVO.getCpf());
			pessoaEntity.setDataNascimento(pessoaVO.getDataNascimento());
			pessoaEntity.setEmail(pessoaVO.getEmail());
		//	pessoaEntity.setEnderecos(EnderecoFactory.converterParaEntity(pessoaVO.getEnderecos()));
			pessoaEntity.setSexo(pessoaVO.getSexo());
			pessoaEntity.setUsuario(UsuarioFactory.converterParaEntity(pessoaVO));
			
			return pessoaEntity;
		}
		return null;
		
	}

}


//private String nome;
//
//private String cpf;
//
//private LocalDate dataNascimento;
//
//private SexoEnum sexo;
//
//private String email;
//
//private String nomeUsuario;
//
//private String senha;
//		
//private ZonedDateTime ultimoAcesso;
//
//private FuncaoNome flagAdm;
//
//private String token;
//
//private List<EnderecoVo> enderecos;