package com.tech.tripon.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comodidade extends NotAuditedEntity{

    @Column(name = "nome")
    private String nome;

}
