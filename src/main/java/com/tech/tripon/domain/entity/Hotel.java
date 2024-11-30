package com.tech.tripon.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Hotel extends AuditedEntity{

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "estrelas")
    private Integer estrelas;

    @Column(name = "quartos")
    private Integer quartos;

    @Column(name = "banheiros")
    private Integer banheiros;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "price")
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    public List<HotelComodidades> comodidades;

    @OneToOne
    private Localidade localizacao;



}
