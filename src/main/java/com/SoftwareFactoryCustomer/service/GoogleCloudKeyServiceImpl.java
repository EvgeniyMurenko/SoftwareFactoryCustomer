package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.GoogleCloudKeyDao;
import com.SoftwareFactoryCustomer.model.GoogleCloudKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("googleCloudKeyService")
public class GoogleCloudKeyServiceImpl implements GoogleCloudKeyService {

    private GoogleCloudKeyDao googleCloudKeyDao;

    @Autowired(required = true)
    public void setGoogleCloudKeyDao(GoogleCloudKeyDao googleCloudKeyDao) {
        this.googleCloudKeyDao = googleCloudKeyDao;
    }


    @Override
    @Transactional
    public void addGoogleCloudKey(GoogleCloudKey googleCloudKey) {
        googleCloudKeyDao.create(googleCloudKey);
    }

    @Override
    @Transactional
    public void updateGoogleCloudKey(GoogleCloudKey googleCloudKey) {
        googleCloudKeyDao.update(googleCloudKey);
    }

    @Override
    @Transactional
    public void deleteGoogleCloudKey(GoogleCloudKey googleCloudKey) {
        googleCloudKeyDao.delete(googleCloudKey);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoogleCloudKey> getAllGoogleCloudKey() {
        return googleCloudKeyDao.findAll();
    }

    @Override
    @Transactional
    public GoogleCloudKey getGoogleCloudKeyById(Long id) {
        return googleCloudKeyDao.read(id);
    }

    @Override
    @Transactional
    public List<String> getAllStringKeys(){
        return googleCloudKeyDao.getAllStringKeys();
    }

    @Override
    @Transactional
    public List<String> findAllKeysByStaff(Long staffInfo){
        return googleCloudKeyDao.findAllKeysByStaff(staffInfo);
    }

}
