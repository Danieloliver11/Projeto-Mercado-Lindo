package com.mercadolindo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.UsuarioEntity;

@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Long>{

	Optional<UsuarioEntity> findByNomeUsuario(String username);

}
