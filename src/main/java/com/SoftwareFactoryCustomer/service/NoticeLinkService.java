package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.NoticeLink;

import java.util.List;

public interface NoticeLinkService {

    void addNewNoticeLink(NoticeLink noticeLink);

    void updateNoticeLink(NoticeLink noticeLink);

    void deleteNoticeLink(NoticeLink noticeLink);

    NoticeLink getNoticeLinkById(Long id);

    List<NoticeLink> getAllNoticeLinks();

}
