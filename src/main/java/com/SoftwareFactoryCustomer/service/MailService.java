package com.SoftwareFactoryCustomer.service;


public interface MailService {

    void sendNaverMailAfterRegistration(String password , String login , String recipientMail , String recipientName );

    void sendNaverMailAfterEstimate(String estimateId , String registrationLink , String recipientMail);

    void sendBugExceptionToEmail(String message);
}
