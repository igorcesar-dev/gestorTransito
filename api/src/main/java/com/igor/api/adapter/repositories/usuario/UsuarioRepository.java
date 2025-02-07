package com.igor.api.adapter.repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.adapter.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {
}
