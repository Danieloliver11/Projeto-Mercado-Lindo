package com.mercadolindo.mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolindo.entity.PessoaEntity;
import com.mercadolindo.entity.UsuarioEntity;
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
@ExtendWith(MockitoExtension.class)
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
		
//		PessoaEntity p = Mockito.mock(PessoaEntity.class);
		PessoaEntity p = new PessoaEntity() ;

		p.setNome("Sr nome");
		p.setId(1l);
		p.setCpf("70597363013");
		p.setDataNascimento(LocalDate.of(1991, 9, 1));
		p.setSexo(SexoEnum.M);
		p.setEmail("jaja@gmail.com");
		
		UsuarioEntity usuario = new UsuarioEntity();
		
		usuario.setNomeUsuario("JAJA");
		usuario.setSenha("1234567");
		usuario.setLogin("jaja@gmail.com");
		
		p.setUsuario(usuario);
		
		PessoaCadastroVO pessoa = criarPessoa();
		
//		Mockito.when(pessoaRepository.findBycpf("70597363013")).thenReturn(Optional.of(p));
		
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

}
