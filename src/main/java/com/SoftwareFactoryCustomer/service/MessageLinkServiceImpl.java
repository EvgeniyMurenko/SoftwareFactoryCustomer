package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.MessageLinkDao;
import com.SoftwareFactoryCustomer.model.MessageLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageLinkService")
public class MessageLinkServiceImpl implements MessageLinkService {

    private MessageLinkDao messageLinkDao;

    @Autowired(required = true)
    public void setMessageLinkDao(MessageLinkDao messageLinkDao) {
        this.messageLinkDao = messageLinkDao;
    }

    @Override
    @Transactional
    public void addNewMessageLink(MessageLink messageLink) {
        messageLinkDao.create(messageLink);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageLink> getAllMessageLinks() {
        return messageLinkDao.findAll();
    }

    @Override
    @Transactional
    public MessageLink getMessageLinkById(Long id) {
        return messageLinkDao.read(id);
    }

    @Override
    @Transactional
    public void updateMessageLink(MessageLink messageLink) {
        messageLinkDao.update(messageLink);
    }

    @Override
    @Transactional
    public void deleteMessageLink(MessageLink messageLink) {
        messageLinkDao.delete(messageLink);
    }

}
