package com.mercadolindo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import com.mercadolindo.repositories.EnderecoRepository;
import com.mercadolindo.repositories.PessoaRepository;
import com.mercadolindo.utils.VerificaDuplicadosUtils;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
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
			CidadeEntity municipioEntity = acharCidadePorId(enderecoVo.getIdMunicipio());
			
			enderecoEntity.setCidade(municipioEntity);
			enderecoEntityList.add(enderecoEntity);
		}
		
		pessoaEntity.setEnderecos(enderecoEntityList);
		
		pessoaRepository.save(pessoaEntity);
				
		return PessoaCadastroVOFactory.converterParaVO(pessoaEntity);

	}
	
	@Transactional
	public PessoaCadastroVO atualizarSenha(AtualizarSenhaVO atualizarSenhaVO) {
				
		PessoaEntity pessoaEntity = acharPessoaProId(atualizarSenhaVO.getId());
		
		
		if(!encode.matches(atualizarSenhaVO.getSenhaAntiga(),pessoaEntity.getUsuario().getSenha())) {
			throw new ParametroInvalidoException("Senha antiga esta errada!");
		}
		
		pessoaEntity.getUsuario().setSenha(encriptarNovaSenha(atualizarSenhaVO));
		
		return PessoaCadastroVOFactory.converterParaVO(pessoaEntity);
		
	}
	
	public PessoaCadastroVO acharPessoa(Long idPessoa) {
		
		PessoaEntity pessoaEntity = acharPessoaProId(idPessoa);

		return PessoaCadastroVOFactory.converterParaVO(pessoaEntity);
				
	}
	
	@Transactional
	public PessoaCadastroVO atualizarPessoa(PessoaCadastroVO pessoaVO) {
		
		//TODO verificar se na lista de enderecos existe pelomenos um endereco princila!

		
		PessoaEntity pessoaEntity = acharPessoaProId(pessoaVO.getId());
		
		VerificaDuplicadosUtils.verificaDuplicado(pessoaVO.getEnderecos());
		verificarQuantidadeEnderecoPrincipal(pessoaVO.getEnderecos());

		validarMunicipio(pessoaVO.getEnderecos());
		
		Optional<PessoaEntity> pessoaEmailOptional = pessoaRepository.findByEmail(pessoaVO.getEmail());
		if(pessoaEmailOptional.isPresent()) {
			PessoaEntity pessoaEmail = pessoaEmailOptional.get();
			
			if(!pessoaEmail.getId().equals(pessoaEntity.getId()) && pessoaEmail.getEmail().equals(pessoaVO.getEmail())) {
				throw new DadosJaCadastradosException("Não é possivel atualizar o E-MAIL! E-MAIL:"+ pessoaVO.getEmail() +  " já cadastrado NO BANCO de dados: ");
			}
		}
				
		List<EnderecoEntity> enderecosBanco = pessoaEntity.getEnderecos();

		if (!enderecosBanco.isEmpty())
			removerEnderecos(enderecosBanco, pessoaVO.getEnderecos());
		
		List<EnderecoEntity> enderecosAtualizados = EnderecoEntityFactory.converterParaAtualizarListaExistenteEntity(enderecosBanco, pessoaVO.getEnderecos());
		
		enderecosAtualizados.forEach(endereco -> {
			endereco.setCidade(acharCidadePorId(endereco.getIdMunicipio())	);
		});
		
		pessoaEntity.setEnderecos(enderecosAtualizados);
		
		PessoaEntity pessoaAtualizadaEntity = PessoaCadastroEntityFactory.converterParaAtualizarEntity(pessoaEntity,pessoaVO);
						
		List<EnderecoEntity> novosEnderesosEntity = new ArrayList<>();
		
		for (EnderecoVo enderecoVO : pessoaVO.getEnderecos()) {
			
			if(enderecoVO.getId() <= 0 || enderecoVO.getId() == null) {
				
				EnderecoEntity converterParaEntity = EnderecoEntityFactory.converterParaEntity(enderecoVO, pessoaEntity);
				CidadeEntity municipioEntity = acharCidadePorId(enderecoVO.getIdMunicipio());
				converterParaEntity.setCidade(municipioEntity);
				
				novosEnderesosEntity.add(converterParaEntity);
			
			}

		}
		
		pessoaAtualizadaEntity.getEnderecos().addAll(novosEnderesosEntity);
		
		return PessoaCadastroVOFactory.converterParaVO(pessoaRepository.save(pessoaAtualizadaEntity));
	
	}
	
	

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

	private void removerEnderecos(List<EnderecoEntity> enderecosBanco, List<EnderecoVo> enderecosAtualizado) {

		List<EnderecoEntity> enderecosRemovidos = enderecosBanco.stream()
				.filter(banco -> enderecosAtualizado.stream()
						.filter(atualizado -> banco.getId().equals(atualizado.getId())).findFirst().isEmpty())
				.collect(Collectors.toList());

		enderecosRemovidos.forEach(remov -> {

			enderecosBanco.remove(remov);
			enderecoRepository.delete(remov);

		});
	}

	
	
	public CidadeEntity acharCidadePorId(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(
					"Nenhuma cidade encontrado pelo ID:" + id));
		

	}







}
