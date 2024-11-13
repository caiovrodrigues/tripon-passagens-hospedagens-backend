package com.tech.tripon.service.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioCreate(
        @Email
        String email,
        String passoword,
        @CPF
        String cpf

) {

}
