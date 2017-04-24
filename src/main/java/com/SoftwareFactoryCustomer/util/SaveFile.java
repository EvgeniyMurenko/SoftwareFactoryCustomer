package com.SoftwareFactoryCustomer.util;

import com.SoftwareFactoryCustomer.constant.MainPathEnum;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class SaveFile {
    private String pathForSaveFile;
    private MultipartFile[] files;

    public SaveFile() {
    }

    public SaveFile(String pathForSaveFile, MultipartFile[] files) {
        this.pathForSaveFile = pathForSaveFile;
        this.files = files;

        File directory = new File(MainPathEnum.mainPath + pathForSaveFile);
        if (!directory.exists()){
            directory.setReadable(true, false);
            directory.setExecutable(true, false);
            directory.setWritable(true, false);
            directory.mkdirs();
            System.out.println("==========CREATE DIR" + directory.getAbsolutePath());
        }
    }

    public void saveFile() {
        for (int i = 0; i < this.files.length; i++) {
            MultipartFile file = this.files[i];
            String name = file.getOriginalFilename();
            saveFile(name, file);
        }

    }

    public void saveVideoFile() {


        String[] extensions = {".mp4", ".avi"};

        for (int i = 0; i < this.files.length; i++) {

            MultipartFile file = this.files[i];
            String name = file.getOriginalFilename();
            String fileExtension = name.substring(name.lastIndexOf('.'), name.length());

            for (String extension : extensions) {
                if (extension.equals(fileExtension)) {
                    saveFile(name, file);
                    System.out.println(name);
                }
            }
        }
    }

    public String getPathForSaveFile() {
        return pathForSaveFile;
    }

    public void setPathForSaveFile(String pathForSaveFile) {
        this.pathForSaveFile = pathForSaveFile;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    private void saveFile(String name, MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            File dir = new File(MainPathEnum.mainPath + this.pathForSaveFile);
       /*     dir.setReadable(true, false);
            dir.setExecutable(true, false);
            dir.setWritable(true, false);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("==========CREATE DIR" + dir.getAbsolutePath());
            }*/
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + "/" + name);

            serverFile.setReadable(true, false);
            serverFile.setExecutable(true, false);
            serverFile.setWritable(true, false);

            serverFile.createNewFile();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

