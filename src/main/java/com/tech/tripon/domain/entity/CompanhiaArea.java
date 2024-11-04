package com.tech.tripon.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CompanhiaArea extends AuditedEntity{

    @Column(name = "nome")
    private String nome;

    @Column(name = "avaliacao")
    private Float avaliacao;

    @Column(name = "logotipo_url")
    private String logotipoUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "companhiaAerea", cascade = CascadeType.ALL)
    private List<Passagem> passagens;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

}
