package com.tech.tripon.web.dto;

import com.tech.tripon.domain.entity.Endereco;
import com.tech.tripon.domain.entity.Usuario;

public record UsuarioInfoResponseDTO(
        String nome,
        String sobrenome,
        String username,
        String email,
        String cpf,
        Endereco endereco
) {

    public UsuarioInfoResponseDTO(Usuario usuario){
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getUsername(), usuario.getEmail(), usuario.getCpf(), usuario.getEndereco());
    }
}
