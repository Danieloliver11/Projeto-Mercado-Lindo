package com.mercadolindo.mock;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolindo.entity.CidadeEntity;
import com.mercadolindo.enums.SexoEnum;
import com.mercadolindo.enums.SimNaoEnum;
import com.mercadolindo.model.EnderecoVo;
import com.mercadolindo.model.PessoaCadastroVO;
import com.mercadolindo.repositories.CidadeRepository;
import com.mercadolindo.repositories.PessoaRepository;
import com.mercadolindo.service.PessoaService;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(Lifecycle.PER_CLASS)
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class PessoaTests {
	
	@InjectMocks
	private PessoaService pessoaService;
	
	@Mock
	PessoaRepository pessoaRepository;

	@Mock
	CidadeRepository cidadeRepository;

	@Test
	@Order(1)
	void deveSalvarPessoa() {
			
		when(cidadeRepository.findById(1l)).thenReturn(Optional.of(new CidadeEntity()));
		when(pessoaRepository.findBycpf("70597363013")).thenReturn(Optional.empty());
		
		PessoaCadastroVO pessoa = criarPessoa();		
		pessoaService.salvarPessoa(pessoa);
	}

	private PessoaCadastroVO criarPessoa() {
		PessoaCadastroVO pessoa = new PessoaCadastroVO();
		
		pessoa.setNome("Sr nome");
		pessoa.setCpf("70597363013");
		pessoa.setDataNascimento(LocalDate.of(1991, 9, 1));
		pessoa.setSexo(SexoEnum.M);
		pessoa.setEmail("jaja@gmail.com");
		pessoa.setNomeUsuario("JAJA");
		pessoa.setSenha("1234567");
		
		pessoa.setEnderecos(criarEndereco());
		
		return pessoa;
	}

	private List<EnderecoVo> criarEndereco() {
		
		EnderecoVo endereco = new EnderecoVo();
		
		endereco.setBairro("Bairro");
		endereco.setCep("0000-00");
		endereco.setNumeroCasa("75");
		endereco.setIdMunicipio(1L);
		endereco.setEnderecoPrincipal(SimNaoEnum.S);
		
		return Arrays.asList(endereco);
	}
	
	@BeforeAll
	private void init() {

		MockitoAnnotations.openMocks(this);
	}

}
