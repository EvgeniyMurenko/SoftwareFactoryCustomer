package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.MessageLink;

import java.util.List;

public interface MessageLinkDao {

    Long create(MessageLink messageLink);

    MessageLink read(Long id);

    void update(MessageLink messageLink);

    void delete(MessageLink messageLink);

    List<MessageLink> findAll();

}
