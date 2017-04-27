package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.EstimateLinkDao;
import com.SoftwareFactoryCustomer.model.EstimateLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("estimateLinkService")
public class EstimateLinkServiceImpl implements EstimateLinkService {

    private EstimateLinkDao estimateLinkDao;

    @Autowired(required = true)
    public void setEstimateDao(EstimateLinkDao estimateLinkDao) {
        this.estimateLinkDao = estimateLinkDao;
    }

    @Override
    @Transactional
    public void addNewEstimateLink(EstimateLink estimateLink) {
        estimateLinkDao.create(estimateLink);
    }

    @Override
    @Transactional
    public void updateEstimateLink(EstimateLink estimateLink) {
        estimateLinkDao.update(estimateLink);
    }

    @Override
    @Transactional
    public void deleteEstimateLink(EstimateLink estimateLink) {
        estimateLinkDao.delete(estimateLink);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstimateLink> getAllEstimateLinks() {
        return estimateLinkDao.findAll();
    }

    @Override
    @Transactional
    public EstimateLink getEstimateLinkById(Long id) {
        return estimateLinkDao.read(id);
    }
    
}

