package com.SoftwareFactoryCustomer.service;


public interface MailService {

    void sendEmailAfterRegistration(String password , String login , String recipientMail , String recipientName );

    void sendEmailAfterEstimate(String estimateId , String registrationLink , String recipientMail);

    void sendEmailAfterEstimateRespond(String recipientMail , String respondText );
}
