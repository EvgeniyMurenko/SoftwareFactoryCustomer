package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.Estimate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("estimateDao")
public class EstimateDaoImpl implements EstimateDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(estimate);
        return id;
    }

    @Override
    public Estimate read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Estimate estimate = (Estimate) session.get(Estimate.class, id);
        return estimate;
    }

    @Override
    public void update(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(estimate);
    }

    @Override
    public void delete(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(estimate);
    }

    @Override
    public List<Estimate> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct estimate from Estimate estimate");
        return query.list();
    }
}
