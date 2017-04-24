package com.SoftwareFactoryCustomer.comparator;

import com.SoftwareFactoryCustomer.model.Message;

import java.util.Date;
import java.util.Comparator;


public class MessageByDateComparator implements Comparator<Message> {

    @Override
    public int compare(Message message1, Message message2) {
        Date messageDate1 = message1.getMessageTime();
        Date messageDate2 = message2.getMessageTime();
        return messageDate1.compareTo(messageDate2);
    }
}

