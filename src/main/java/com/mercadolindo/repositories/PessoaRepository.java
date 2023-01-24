package com.mercadolindo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

	Optional<PessoaEntity> findBycpf(String cpf);

	Optional<PessoaEntity> findByEmail(String email);
	
}
