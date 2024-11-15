package com.tech.tripon.web.dto;

import com.tech.tripon.domain.entity.Usuario;

import java.util.List;

public record UsuarioResponseDTO(String username, String email, List<String> roles) {

    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getUsername(), usuario.getEmail(), usuario.getUsuarioRoles().stream().map(usuarioRole -> usuarioRole.getRole().getNome()).toList());
    }

}
