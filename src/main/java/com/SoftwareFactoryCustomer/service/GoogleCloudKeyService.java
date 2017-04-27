package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.GoogleCloudKey;

import java.util.List;

public interface GoogleCloudKeyService {

    void addGoogleCloudKey(GoogleCloudKey googleCloudKey);

    void updateGoogleCloudKey(GoogleCloudKey googleCloudKey);

    void deleteGoogleCloudKey(GoogleCloudKey googleCloudKey);

    List<GoogleCloudKey> getAllGoogleCloudKey();

    GoogleCloudKey getGoogleCloudKeyById(Long id);

    List<String> getAllStringKeys();

    List<String> findAllKeysByStaff(Long staffInfo);

}
