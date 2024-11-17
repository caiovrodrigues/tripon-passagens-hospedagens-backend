package com.tech.tripon.web.dto;


import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioCreate(
        String username,
        @Email
        String email,
        String password,
        @CPF
        String cpf,
        String passwordConfirm

) {

}
