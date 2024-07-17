package com.mockproject.AuctionManagement.repository;

import com.mockproject.AuctionManagement.dto.response.AssetResponseDTO;
import com.mockproject.AuctionManagement.dto.response.PageResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetRepositoryCustome {

    @PersistenceContext
    private EntityManager entityManager;

    public PageResponse<?> findAllAssetByIdSerller(int idSerller, int page, int size) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.id_asset, a.asset_name, a.description, a.estimated_value, a.size, a.origin, a.property_status, ca.name, a.quantity ");
        sql.append("from tbl_asset a join tbl_category_asset ca on a.id_cate_asset = ca.id_cate_asset ");
        sql.append("where 1=1");
        sql.append("and a.id_seller = :id ");
        sql.append("and a.status = 1");

        Query selectQuery = entityManager.createNativeQuery(sql.toString());
        selectQuery.setParameter("id", idSerller);
        selectQuery.setFirstResult(page -1);
        selectQuery.setMaxResults(size);
        var asset = selectQuery.getResultList();

        StringBuilder count = new StringBuilder();
        count.append("select count(a) from AssetEntity a ");
        count.append("where 1=1");
        count.append("and a.seller.idUser = :id ");
        count.append("and a.status = 1");

        Query countQuery = entityManager.createQuery(count.toString());
        countQuery.setParameter("id", idSerller);
        Long toTal = (Long) countQuery.getSingleResult();

        int toTalPage = toTal.intValue()/size;
        if (toTalPage == 0) {
            toTalPage = 1;
        }
        return PageResponse.builder()
                .pageNo(page)
                .pageSize(size)
                .totalPage(toTalPage)
                .items(asset)
                .build();
    }
}
