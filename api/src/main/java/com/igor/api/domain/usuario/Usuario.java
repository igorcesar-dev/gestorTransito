package com.igor.api.domain.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String login;
    @JsonIgnore
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Nenhuma autoridade específica
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true; // Indica que a conta não está expirada
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true; // Indica que a conta não está bloqueada
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true; // Indica que as credenciais não estão expiradas
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true; // Indica que o usuário está habilitado
    }

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }
}
