package com.tech.tripon.web.dto;

import java.math.BigDecimal;

public record PassagemEditarDTO(
        Integer id,
        String origem,
        String destino,
        Double preco
) {
}
