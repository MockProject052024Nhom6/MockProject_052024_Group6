package com.mockproject.AuctionManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_tax")
public class TaxEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tax")
    private Integer idTax;

    @Column(name = "tax_name")
    private String taxName;

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer endTime;

    @OneToMany(mappedBy = "taxEntity")
    private Set<TaxHasContractEntity> taxHasContractEntities = new HashSet<>();
}
