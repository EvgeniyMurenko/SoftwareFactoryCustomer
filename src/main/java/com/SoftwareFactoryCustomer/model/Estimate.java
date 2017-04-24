package com.SoftwareFactoryCustomer.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "s_estimates")
public class Estimate {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "estimate_id")
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "estimate_request", nullable = false)
    private String estimateRequest;

    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "is_respond")
    private boolean isRespond;


    @Column(name = "is_price_request")
    private boolean isPriceRequest;


    @Column(name = "is_question_request")
    private boolean isQuestionRequest;


    @Column(name = "phone", nullable = false)
    private String phone;


    @Column(name = "date_request", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRequest;


    @Column(name = "estimate_generated_id")
    private String estimateGeneratedId;


    @Column(name = "path")
    private String estimatePath;

    @OneToOne
    @JoinColumn(name="customer_info_id")
    CustomerInfo customerInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRespond() {
        return isRespond;
    }

    public void setRespond(boolean respond) {
        isRespond = respond;
    }

    public boolean isPriceRequest() {
        return isPriceRequest;
    }

    public void setPriceRequest(boolean priceRequest) {
        isPriceRequest = priceRequest;
    }

    public boolean isQuestionRequest() {
        return isQuestionRequest;
    }

    public void setQuestionRequest(boolean questionRequest) {
        isQuestionRequest = questionRequest;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getEstimateGeneratedId() {
        return estimateGeneratedId;
    }

    public void setEstimateGeneratedId(String estimateGeneratedId) {
        this.estimateGeneratedId = estimateGeneratedId;
    }

    public String getEstimateRequest() {
        return estimateRequest;
    }

    public void setEstimateRequest(String estimateRequest) {
        this.estimateRequest = estimateRequest;
    }

    public String getEstimatePath() {
        return estimatePath;
    }

    public void setEstimatePath(String estimatePath) {
        this.estimatePath = estimatePath;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Estimate() {
    }

    public Estimate(String name, String estimateRequest, String email, boolean isRespond, boolean isPriceRequest, boolean isQuestionRequest, String phone, Date dateRequest, String estimateGeneratedId, String estimatePath) {
        this.name = name;
        this.estimateRequest = estimateRequest;
        this.email = email;
        this.isRespond = isRespond;
        this.isPriceRequest = isPriceRequest;
        this.isQuestionRequest = isQuestionRequest;
        this.phone = phone;
        this.dateRequest = dateRequest;
        this.estimateGeneratedId = estimateGeneratedId;
        this.estimatePath = estimatePath;
    }
}
