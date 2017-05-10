package com.SoftwareFactoryCustomer.service;

import com.google.android.gcm.server.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PushNotificationService {

    /**
     * keys is the id which android app will give to server (one time)
     **/

    @Autowired
    GoogleCloudKeyService googleCloudKeyService;

    public boolean pushNotificationToGCM(String message , String title){

        List<String> keys = googleCloudKeyService.getAllStringKeys();

        final String GCM_API_KEY = "AAAA6kxQc60:APA91bHuM7g4FlPb7jxjoB-Fj3frfpgSzZ962WZWjy7MOxKNYdqW2zYQM0ST_c9HNC1-zpknSzoQVMyvTjvz82x2bD71PEs4mUmHiQn140TzSjNzBmInN3BVMUdby1dlRIYovnkxnSJ6";
        final int retries = 3;
        Sender sender = new Sender(GCM_API_KEY);

        Message msg = new Message.Builder().collapseKey("gcm_message").delayWhileIdle(true).addData("message",message).addData("title" , title).build();

        try {

            MulticastResult result = sender.send(msg, keys, retries);

            System.out.println("GCM Notification is sent successfully" + result.toString());

        } catch (InvalidRequestException e) {
            System.out.println("Invalid Request");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return false;

    }
}