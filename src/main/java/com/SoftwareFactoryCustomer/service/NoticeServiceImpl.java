package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.NoticeDao;
import com.SoftwareFactoryCustomer.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao;

    @Autowired(required = true)
    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }


    @Override
    @Transactional
    public void addNewNotice(Notice notice) {
        noticeDao.create(notice);
    }

    @Override
    @Transactional
    public void updateNotice(Notice notice) {
        noticeDao.update(notice);
    }

    @Override
    @Transactional
    public void deleteNotice(Notice notice) {
        noticeDao.delete(notice);
    }

    @Override
    @Transactional
    public Notice getNoticeById(Long id) {
        return noticeDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> getAllNotices() {
        return noticeDao.findAll();
    }

}
