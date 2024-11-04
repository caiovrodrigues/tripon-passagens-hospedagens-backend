package com.tech.tripon.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CompraPassagem extends AuditedEntity{

    @NotNull
    @JoinColumn(name = "passagem")
    @ManyToOne
    private Passagem passagem;

    @NotNull
    @JoinColumn(name = "cliente")
    @ManyToOne
    private Usuario cliente;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "check_in")
    private boolean checkIn;

    public Double getTotal(){
        return passagem.getPreco() * quantidade;
    }

}
