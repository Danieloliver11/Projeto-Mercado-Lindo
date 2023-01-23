package com.mercadolindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.exception.DadosJaCadastradosException;
import com.mercadolindo.exception.NaoEncontradoException;
import com.mercadolindo.factory.entityFactory.EnderecoFactory;
import com.mercadolindo.factory.entityFactory.PessoaCadastroFactory;
import com.mercadolindo.model.EnderecoVo;
import com.mercadolindo.model.PessoaCadastroVO;
import com.mercadolindo.repositories.CidadeRepository;
import com.mercadolindo.repositories.PessoaRepository;
import com.mercadolindo.utils.VerificaDuplicadosUtils;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CidadeRepository cidadeRepository;
	
	public Void salvarPessoa(PessoaCadastroVO pessoaVO) {

		VerificaDuplicadosUtils.verificaDuplicado(pessoaVO.getEnderecos());
		verificarCpfExistenteNoBanco(pessoaVO.getCpf());
		validarMunicipio(pessoaVO.getEnderecos());

		PessoaEntity pessoaEntity = PessoaCadastroFactory.converterParaEntity(pessoaVO);
		pessoaEntity.setEnderecos(EnderecoFactory.converterParaEntity(pessoaVO.getEnderecos()));
		
		pessoaRepository.save(pessoaEntity);

		return null;
	}

	private void verificarCpfExistenteNoBanco(String cpf) {

		PessoaEntity pessoaEntity = pessoaRepository.findBycpf(cpf);

		if (pessoaEntity != null) {
			throw new DadosJaCadastradosException("CPF:"+cpf+" já cadastrado no sistema!");
		}

	}

	private void validarMunicipio(List<EnderecoVo> Enderecos) {

		Enderecos.forEach(endereco -> {
			cidadeRepository.findById(endereco.getIdMunicipio()).orElseThrow(() -> new NaoEncontradoException(
					"Nenhum Municipio encontrado pelo ID:" + endereco.getIdMunicipio()));

		});
	}



}
