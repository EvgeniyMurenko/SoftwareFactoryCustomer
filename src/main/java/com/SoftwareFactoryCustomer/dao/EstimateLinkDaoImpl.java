package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.EstimateLink;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("estimateLinkDao")
public class EstimateLinkDaoImpl implements EstimateLinkDao {

    static final Logger logger = LoggerFactory.getLogger(EstimateDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(EstimateLink estimateLink) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(estimateLink);
        return id;
    }

    @Override
    public EstimateLink read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        EstimateLink estimateLink = (EstimateLink) session.get(EstimateLink.class, id);
        return estimateLink;
    }

    @Override
    public void update(EstimateLink estimateLink) {
        Session session = sessionFactory.getCurrentSession();
        session.update(estimateLink);
        logger.error("EstimateLink update successfully, EstimateLink=" + estimateLink);
    }

    @Override
    public void delete(EstimateLink estimateLink) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(estimateLink);
        logger.info("EstimateLink deleted successfully, EstimateLink details=" + estimateLink);
    }

    @Override
    public List<EstimateLink> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EstimateLink");
        return query.list();
    }
}
