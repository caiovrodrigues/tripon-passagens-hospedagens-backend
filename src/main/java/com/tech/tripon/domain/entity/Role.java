package com.tech.tripon.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Role extends NotAuditedEntity {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Column(name = "nome", unique = true)
    private String nome;

}
