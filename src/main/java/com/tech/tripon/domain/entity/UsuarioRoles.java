package com.tech.tripon.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
public class UsuarioRoles extends AuditedEntity{

    @NotNull
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;

}
