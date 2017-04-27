package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.NoticeLinkDao;
import com.SoftwareFactoryCustomer.model.NoticeLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("noticeLinkService")
public class NoticeLinkServiceImpl implements NoticeLinkService{

    private NoticeLinkDao noticeLinkDao;

    @Autowired(required = true)
    public void setNoticeDao(NoticeLinkDao noticeLinkDao) {
        this.noticeLinkDao = noticeLinkDao;
    }


    @Override
    @Transactional
    public void addNewNoticeLink(NoticeLink noticeLink) {
        noticeLinkDao.create(noticeLink);
    }

    @Override
    @Transactional
    public void updateNoticeLink(NoticeLink noticeLink) {
        noticeLinkDao.update(noticeLink);
    }

    @Override
    @Transactional
    public void deleteNoticeLink(NoticeLink noticeLink) {
        noticeLinkDao.delete(noticeLink);
    }

    @Override
    @Transactional
    public NoticeLink getNoticeLinkById(Long id) {
        return noticeLinkDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeLink> getAllNoticeLinks() {
        return noticeLinkDao.findAll();
    }

}
