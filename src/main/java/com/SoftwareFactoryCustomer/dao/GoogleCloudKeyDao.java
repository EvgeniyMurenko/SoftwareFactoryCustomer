package com.SoftwareFactoryCustomer.dao;

import com.SoftwareFactoryCustomer.model.GoogleCloudKey;

import java.util.List;


public interface GoogleCloudKeyDao {
    Long create(GoogleCloudKey googleCloudKey);
    GoogleCloudKey read(Long id);
    void update(GoogleCloudKey googleCloudKey);
    void delete(GoogleCloudKey googleCloudKey);
    List<GoogleCloudKey> findAll();
    List<String> getAllStringKeys();
    List<String> findAllKeysByStaff(Long staffInfo);
}
