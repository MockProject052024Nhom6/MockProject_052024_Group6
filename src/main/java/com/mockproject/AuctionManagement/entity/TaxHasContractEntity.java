package com.mockproject.AuctionManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_tax_has_contract")
public class TaxHasContractEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tax_contract")
    private Integer idTaxContract;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @ManyToOne
    @JoinColumn(name = "id_tax")
    private TaxEntity taxEntity;

    @ManyToOne
    @JoinColumn(name = "id_contract")
    private ContractEntity contractEntity;
}
