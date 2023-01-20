package com.mercadolindo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolindo.model.PessoaCadastroVO;
import com.mercadolindo.service.PessoaService;

@RestController
@RequestMapping("enderecopessoafisica")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	
	private PessoaService pessoaService;
	
	@PostMapping
	public Void salvarPessoa(@RequestBody PessoaCadastroVO pessoaVO) {
		return pessoaService.salvarPessoa(pessoaVO);
	}

}
