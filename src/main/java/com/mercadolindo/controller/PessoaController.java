package com.mercadolindo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolindo.model.AtualizarSenhaVO;
import com.mercadolindo.model.PessoaCadastroVO;
import com.mercadolindo.service.PessoaService;

@RestController
@RequestMapping("enderecopessoafisica")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PessoaCadastroVO salvarPessoa(@Valid @RequestBody PessoaCadastroVO pessoaVO) {
		return pessoaService.salvarPessoa(pessoaVO);
	}
	
	@PutMapping
	public PessoaCadastroVO atualizarSenha(@Valid @RequestBody AtualizarSenhaVO atualizarSenhaVO) {
		return pessoaService.atualizarSenha(atualizarSenhaVO);
	}
	
	@GetMapping("/{idPessoa}")
	public PessoaCadastroVO acharPessoa(@PathVariable Long idPessoa) {
		return pessoaService.acharPessoa(idPessoa);
	}
	
	@PutMapping("/atualiza-pessoa")
	public PessoaCadastroVO atualizarPessoa(@Valid @RequestBody PessoaCadastroVO pessoaVO) {
		return pessoaService.atualizarPessoa(pessoaVO);
	}

}
