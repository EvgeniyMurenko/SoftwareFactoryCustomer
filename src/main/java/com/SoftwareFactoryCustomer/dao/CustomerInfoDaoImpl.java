package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.CustomerInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("customerInfoDao")
public class CustomerInfoDaoImpl implements CustomerInfoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(CustomerInfo customerInfo) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(customerInfo);
        return id;
    }

    @Override
    public CustomerInfo read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct customerInfo from CustomerInfo customerInfo " +
                "left join fetch customerInfo.user " +
                "left join fetch customerInfo.projects pr " +
                "left join fetch pr.cases aCase " +
                "left join fetch aCase.messages " +
                "where customerInfo.id = :id");
        query.setParameter("id", id);
        return (CustomerInfo) query.uniqueResult();
    }

    @Override
    public void update(CustomerInfo customerInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customerInfo);
    }

    @Override
    public void delete(CustomerInfo customerInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customerInfo);
    }

    @Override
    public List<CustomerInfo> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CustomerInfo");
        return query.list();
    }
}
