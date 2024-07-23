package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.TypeMedia;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_asset_media")
public class AssetMediaEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_media")
    private Long idMedia;

    @Column(name = "link")
    private String link;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeMedia type;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_asset")
    private AssetEntity assetEntity;
}
