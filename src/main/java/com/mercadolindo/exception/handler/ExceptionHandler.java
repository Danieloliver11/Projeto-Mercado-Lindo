package com.mercadolindo.exception.handler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mercadolindo.exception.DadosJaCadastradosException;
import com.mercadolindo.exception.MsgException;
import com.mercadolindo.exception.NaoEncontradoException;
import com.mercadolindo.exception.ParametroInvalidoException;




@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String HEADER_MESSAGE = "mensagem";

	private static final String TITLE_ERRO_REGRA_NEGOCIO = "Regra de negócio";
	private static final String TITLE_PARAMETROS_INVALIDOS = "Parâmetros inválidos";
	private static final String TITLE_DADOS_JA_CADASTRADOS = "Dados já cadastrados";
	private static final String TITLE_ERRO_SERVIDOR = "Erro no servidor";

	private static final String TYPE_VALIDACAO_REGRA_NEGOCIO = "Validação de regras de negócio";
	private static final String TYPE_VALIDACAO_PARAMETROS = "Validação de Parâmetros";
	private static final String TYPE_ERRO_INESPERADO = "Erro inesperado";

	@org.springframework.web.bind.annotation.ExceptionHandler(MsgException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(MsgException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_ERRO_REGRA_NEGOCIO,
				TYPE_VALIDACAO_REGRA_NEGOCIO, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ParametroInvalidoException.class)
	public ResponseEntity<Object> handleParametroInvalidoException(ParametroInvalidoException e, ServletWebRequest request) {
		logger.warn(e.getMessage().toUpperCase());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_PARAMETROS_INVALIDOS,
				TYPE_VALIDACAO_PARAMETROS, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(DadosJaCadastradosException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(DadosJaCadastradosException e, ServletWebRequest request) {
		logger.warn(e.getMessage().toUpperCase());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_DADOS_JA_CADASTRADOS,
				TYPE_VALIDACAO_PARAMETROS, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handleNaoEncontradoException(NaoEncontradoException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, null, header, NOT_FOUND, request);
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_PARAMETROS_INVALIDOS,
				TYPE_VALIDACAO_PARAMETROS, e.getAllErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
				request.getDescription(false).replace("uri=", ""));

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getObjectName());

		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.BAD_REQUEST, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaughtException(Exception e, ServletWebRequest request) {
		logger.error(e.getMessage(), e);

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_ERRO_SERVIDOR, TYPE_ERRO_INESPERADO,
				Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, INTERNAL_SERVER_ERROR, request);
	}

	private ExceptionResponseVO criarExceptionResponse(String title, String type, List<String> detail, String instance) {
		
		ExceptionResponseVO vo = new ExceptionResponseVO();
		
		vo.setDetail(detail);
		vo.setInstance(instance);
		vo.setTitle(title);
		vo.setType(type);
		
		return vo;
	}


}
