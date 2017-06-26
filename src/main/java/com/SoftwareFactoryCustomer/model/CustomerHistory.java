package com.SoftwareFactoryCustomer.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "s_customer_history")
public class CustomerHistory {

    public CustomerHistory(){}


    public CustomerHistory(String text, Date date, CustomerInfo customerInfo, String managerName, Long managerId) {
        this.text = text;
        this.date = date;
        this.customerInfo = customerInfo;
        this.managerName = managerName;
        this.managerId = managerId;
    }


    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;


    @Column(name = "text")
    private String text;


    @Column(name = "date")
    private Date date;


    @ManyToOne
    @JoinColumn(name = "customer_info_id")
    private CustomerInfo customerInfo;


    @Column(name = "manager_name")
    private String managerName;


    @Column(name = "manager_id")
    private Long managerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

}
