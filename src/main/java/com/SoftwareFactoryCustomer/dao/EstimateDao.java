package com.SoftwareFactoryCustomer.dao;

import com.SoftwareFactoryCustomer.model.Estimate;

import java.util.List;

public interface  EstimateDao {

    public Long create(Estimate estimate);
    Estimate read(Long id);
    void update(Estimate estimate);
    void delete(Estimate estimate);
    List<Estimate> findAll();

}
