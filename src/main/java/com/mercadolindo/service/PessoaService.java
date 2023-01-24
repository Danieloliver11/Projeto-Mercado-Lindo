package com.mercadolindo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mercadolindo.entity.CidadeEntity;
import com.mercadolindo.entity.EnderecoEntity;
import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.enums.SimNaoEnum;
import com.mercadolindo.exception.DadosJaCadastradosException;
import com.mercadolindo.exception.NaoEncontradoException;
import com.mercadolindo.exception.ParametroInvalidoException;
import com.mercadolindo.factory.entityFactory.EnderecoEntityFactory;
import com.mercadolindo.factory.entityFactory.PessoaCadastroEntityFactory;
import com.mercadolindo.factory.voFactory.PessoaCadastroVOFactory;
import com.mercadolindo.model.AtualizarSenhaVO;
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
	
	BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
	
	@Transactional
	public PessoaCadastroVO salvarPessoa(PessoaCadastroVO pessoaVO) {
		
		List<EnderecoEntity> enderecoEntityList = new ArrayList<>();

		VerificaDuplicadosUtils.verificaDuplicado(pessoaVO.getEnderecos());
		verificarQuantidadeEnderecoPrincipal(pessoaVO.getEnderecos());
		verificarCpfExistenteNoBanco(pessoaVO.getCpf());
		verificarEmailExistenteNoBanco(pessoaVO.getEmail());
		validarMunicipio(pessoaVO.getEnderecos());
		encriptarSenha(pessoaVO);

		PessoaEntity pessoaEntity = PessoaCadastroEntityFactory.converterParaEntity(pessoaVO);
		
		for (EnderecoVo enderecoVo : pessoaVO.getEnderecos()) {
			
			EnderecoEntity enderecoEntity = EnderecoEntityFactory.converterParaEntity(enderecoVo, pessoaEntity);
			Optional<CidadeEntity> municipioEntity = cidadeRepository.findById(enderecoVo.getIdMunicipio());
			
			enderecoEntity.setCidade(municipioEntity.get());
			enderecoEntityList.add(enderecoEntity);
		}
		
		pessoaEntity.setEnderecos(enderecoEntityList);
		
		
				
		return PessoaCadastroVOFactory.converterParaVO(pessoaRepository.save(pessoaEntity));

	}

	public Void atualizarSenha(@Valid AtualizarSenhaVO atualizarSenhaVO) {
		
		PessoaEntity pessoaEntity = acharPessoaProId(atualizarSenhaVO.getId());
		
		
		if(!encode.matches(atualizarSenhaVO.getSenhaAntiga(),pessoaEntity.getUsuario().getSenha())) {
			throw new ParametroInvalidoException("Senha antiga esta errada!");
		}
		
		pessoaEntity.getUsuario().setSenha(encriptarNovaSenha(atualizarSenhaVO));
		
		pessoaRepository.save(pessoaEntity);
		
		return null;
	}
	
	//verificar endereco principal no put
	
	
	
	

	private String encriptarNovaSenha(AtualizarSenhaVO atualizarSenhaVO) {
		return encode.encode(atualizarSenhaVO.getSenhaNova());
		
	}



	private PessoaEntity acharPessoaProId(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(
				"Nenhuma Pessoa encontrado pelo ID:" + id));
		
	}

	private void encriptarSenha(PessoaCadastroVO pessoaVO) {
		
		
		String senhaEncoder = encode.encode(pessoaVO.getSenha());
		pessoaVO.setSenha(senhaEncoder);

	}

	private void verificarCpfExistenteNoBanco(String cpf) {

		Optional<PessoaEntity> pessoaEntity = pessoaRepository.findBycpf(cpf);

		if (pessoaEntity.isPresent()) {
			throw new DadosJaCadastradosException("CPF:"+cpf+" já cadastrado no sistema!");
		}

	}
	
	private void verificarEmailExistenteNoBanco(String email) {
		Optional<PessoaEntity> pessoaEntity = pessoaRepository.findByEmail(email);

		if (pessoaEntity.isPresent()) {
			throw new DadosJaCadastradosException("E-MAIL:"+email+" já cadastrado no sistema!");
		}
		
	}

	private void validarMunicipio(List<EnderecoVo> Enderecos) {

		Enderecos.forEach(endereco -> {
			cidadeRepository.findById(endereco.getIdMunicipio()).orElseThrow(() -> new NaoEncontradoException(
					"Nenhum Municipio encontrado pelo ID:" + endereco.getIdMunicipio()));

		});
	}
	
	private void verificarQuantidadeEnderecoPrincipal(List<EnderecoVo> enderecos) {
		int quantidade = 0;
		for (EnderecoVo enderecoVo : enderecos) {
			if(enderecoVo.getEnderecoPrincipal().equals(SimNaoEnum.S)) {
				quantidade += 1;
				if(quantidade > 1)
					throw new ParametroInvalidoException("Não é possível cadastrar mais de um endereço principal!");
			}
		}
	}







}
