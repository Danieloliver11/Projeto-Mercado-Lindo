package com.mercadolindo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.repositories.UsuarioRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	public UserDetailsServiceImpl(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}

	private final UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		return userRepository.findByNomeUsuario(username).orElseThrow(() 
				-> new UsernameNotFoundException("Usuário não encontrado!"));
	}

}
