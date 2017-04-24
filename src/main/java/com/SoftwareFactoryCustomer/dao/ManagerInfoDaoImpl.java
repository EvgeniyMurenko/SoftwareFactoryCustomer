package com.SoftwareFactoryCustomer.dao;

import com.SoftwareFactoryCustomer.model.ManagerInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("managerInfoDao")
public class ManagerInfoDaoImpl implements ManagerInfoDao {

    private static final Logger logger = LoggerFactory.getLogger(ManagerInfoDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(ManagerInfo managerInfo) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(managerInfo);
        return id;
    }

    @Override
    public ManagerInfo read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ManagerInfo managerInfo = (ManagerInfo) session.get(ManagerInfo.class, id);
        logger.error("ManagerInfo read successfully, ManagerInfo=" + managerInfo);
        return managerInfo;
    }

    @Override
    public void update(ManagerInfo managerInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.update(managerInfo);
        logger.error("ManagerInfo update successfully, ManagerInfo=" + managerInfo);
    }

    @Override
    public void delete(ManagerInfo managerInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(managerInfo);
        logger.info("ManagerInfo deleted successfully, ManagerInfo details=" + managerInfo);
    }

    @Override
    public List<ManagerInfo> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ManagerInfo");
        return query.list();
    }
}

