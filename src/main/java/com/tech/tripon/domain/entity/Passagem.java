package com.tech.tripon.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Passagem extends AuditedEntity{

    @JoinColumn(name = "origem")
    @ManyToOne
    private Localidade origem;

    @JoinColumn(name = "destino")
    @ManyToOne
    private Localidade destino;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "data_ida")
    private LocalDateTime dataIda;

    @Column(name = "data_volta")
    private LocalDateTime dataVolta;

    @JoinColumn(name = "companhia_aerea")
    @ManyToOne
    private CompanhiaArea companhiaAerea;

    @ManyToOne
    private Hotel hotel;

}
