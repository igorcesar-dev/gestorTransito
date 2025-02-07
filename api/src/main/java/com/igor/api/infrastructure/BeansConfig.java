package com.igor.api.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.igor.api.core.ports.ocorrencia.OcorrenciaRepositoryPort;
import com.igor.api.core.ports.ocorrencia.OcorrenciaServicePort;
import com.igor.api.core.ports.usuario.UsuarioRepositoryPort;
import com.igor.api.core.ports.usuario.UsuarioServicePort;
import com.igor.api.core.services.OcorrenciaService;
import com.igor.api.core.services.UsuarioService;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public UsuarioServicePort usuarioServiceImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(usuarioRepositoryPort);
    }

    @Bean
    public OcorrenciaServicePort ocorrenciaServiceImpl(OcorrenciaRepositoryPort ocorrenciaRepositoryPort) {
        return new OcorrenciaService(ocorrenciaRepositoryPort);
    }
}
