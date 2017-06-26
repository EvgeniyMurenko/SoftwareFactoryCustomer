package com.SoftwareFactoryCustomer.util;

import com.SoftwareFactoryCustomer.constant.GlobalEnum;
import com.SoftwareFactoryCustomer.constant.MainPathEnum;

import com.SoftwareFactoryCustomer.model.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SaveFile {

    private String pathForSaveFile;
    private MultipartFile[] files;


    public SaveFile(MultipartFile[] files) {
        if (files.length > 0) {
            this.files = files;
        } else {
            this.files = new MultipartFile[0];
        }
    }


    public void saveNoticeFilesToNotice(Notice notice) {

        if (files.length < 1) return;
        if (files[0].isEmpty()) return;

        pathForSaveFile = MainPathEnum.mainPath + "/notice/";

        List<NoticeLink> noticeLinks = new ArrayList<>(notice.getNoticeLinks());

        for (MultipartFile file : this.files) {
            try {
                String name = file.getOriginalFilename();
                String generatedName = generateUUIDname(name);
                String link = GlobalEnum.webRoot + "/get-file/notice/" + generatedName;
                saveFile(generatedName, file);
                NoticeLink noticeLink = new NoticeLink(notice, link, name, generatedName);
                noticeLinks.add(noticeLink);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveMessageFilesToMessage(Message message) {
        if (files.length < 1) return;
        if (files[0].isEmpty()) return;

        pathForSaveFile = MainPathEnum.mainPath + "/message/";

        Set<MessageLink> messageLinks = message.getMessageLinks();

        for (MultipartFile file : this.files) {
            try {
                String name = file.getOriginalFilename();
                String generatedName = generateUUIDname(name);
                String link = GlobalEnum.webRoot + "/get-file/message/" + generatedName;
                saveFile(generatedName, file);
                MessageLink messageLink = new MessageLink(message, link, name, generatedName);
                messageLinks.add(messageLink);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveEstimateFilesToEstimate(Estimate estimate) {

        if (files.length < 1) return;
        if (files[0].isEmpty()) return;

        pathForSaveFile = MainPathEnum.mainPath + "/estimate/";

        Set<EstimateLink> estimateLinks = estimate.getEstimateLinks();

        for (MultipartFile file : this.files) {
            try {
                String name = file.getOriginalFilename();
                String generatedName = generateUUIDname(name);
                String link = GlobalEnum.webRoot + "/get-file/estimate/" + generatedName;
                saveFile(generatedName, file);
                EstimateLink estimateLink = new EstimateLink(estimate, link, name, generatedName);
                estimateLinks.add(estimateLink);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(String name, MultipartFile file) throws IOException {

        if (files.length < 1) return;
        if (files[0].isEmpty()) return;

        byte[] bytes = file.getBytes();
        File directory = new File(this.pathForSaveFile);
        directory.setReadable(true, false);
        directory.setExecutable(true, false);
        directory.setWritable(true, false);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("==========CREATE DIR" + directory.getAbsolutePath());
        }
        // Create the file on server
        File serverFile = new File(directory.getAbsolutePath() + "/" + name);

        serverFile.setReadable(true, false);
        serverFile.setExecutable(true, false);
        serverFile.setWritable(true, false);

        serverFile.createNewFile();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }


    private String generateUUIDname(String originalFileName) {

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String generatedUUIDname = (java.util.UUID.randomUUID() + fileExtension);
        return generatedUUIDname;

    }

}