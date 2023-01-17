package com.mercadolindo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.repositories.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		return userRepository.findByNomeUsuario(username).orElseThrow(() 
				-> new UsernameNotFoundException("Usuário não encontrado!"));
	}

}
