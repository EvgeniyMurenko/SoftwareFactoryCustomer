package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.EstimateLink;

import java.util.List;

public interface EstimateLinkDao {

    Long create(EstimateLink estimateLink);

    EstimateLink read(Long id);

    void update(EstimateLink estimateLink);

    void delete(EstimateLink estimateLink);

    List<EstimateLink> findAll();

}
