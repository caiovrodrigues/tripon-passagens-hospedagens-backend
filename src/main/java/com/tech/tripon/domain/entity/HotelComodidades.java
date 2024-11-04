package com.tech.tripon.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"hotel_id", "comodidade_id"})})
public class HotelComodidades extends NotAuditedEntity {

    @NotNull
    @JoinColumn(name = "hotel_id")
    @ManyToOne
    private Hotel hotel;

    @NotNull
    @JoinColumn(name = "comodidade_id")
    @ManyToOne
    private Comodidade comodidade;

}
