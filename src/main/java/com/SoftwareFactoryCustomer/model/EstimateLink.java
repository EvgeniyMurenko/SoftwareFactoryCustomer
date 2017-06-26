package com.SoftwareFactoryCustomer.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_estimate_link")
public class EstimateLink implements Serializable {

    public EstimateLink(){}

    public EstimateLink(Estimate estimate, String fileLink, String fileName, String fileUuidName) {
        this.estimate = estimate;
        this.fileLink = fileLink;
        this.fileName = fileName;
        this.fileUuidName = fileUuidName;
    }


    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estimate_id")
    private Estimate estimate;

    @Column(name = "file_link")
    private String fileLink;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_uuid_name")
    private String fileUuidName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUuidName() {
        return fileUuidName;
    }

    public void setFileUuidName(String fileUuidName) {
        this.fileUuidName = fileUuidName;
    }
}
