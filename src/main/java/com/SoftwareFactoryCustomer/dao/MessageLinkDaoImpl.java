package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.MessageLink;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageLinkDao")
public class MessageLinkDaoImpl implements MessageLinkDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Long create(MessageLink messageLink) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(messageLink);
        return id;
    }


    @Override
    public MessageLink read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        MessageLink messageLink = (MessageLink) session.get(MessageLink.class, id);
        return messageLink;
    }

    @Override
    public void update(MessageLink messageLink) {
        Session session = sessionFactory.getCurrentSession();
        session.update(messageLink);
        logger.error("MessageLink update successfully, MessageLink=" + messageLink);
    }

    @Override
    public void delete(MessageLink messageLink) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(messageLink);
        logger.info("MessageLink deleted successfully, MessageLink details=" + messageLink);
    }

    @Override
    public List<MessageLink> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MessageLink");
        return query.list();
    }
}
