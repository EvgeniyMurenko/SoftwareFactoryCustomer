package com.SoftwareFactoryCustomer.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "s_customer_info")
public class CustomerInfo implements Serializable {

    public CustomerInfo() {
    }

    public CustomerInfo(Long id, User user, String name, String company, String phone, String email, String website, boolean isFullCreated, boolean isStandardAccount, String directorsName, String directorsEmail, String directorsPhone, String companyType, String address, Date registrationDate) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.isFullCreated = isFullCreated;
        this.isStandardAccount = isStandardAccount;
        this.directorsName = directorsName;
        this.directorsEmail = directorsEmail;
        this.directorsPhone = directorsPhone;
        this.companyType = companyType;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name="web_site")
    private String website;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projects;

    @Column(name="is_full_created")
    private boolean isFullCreated;

    @Column(name = "is_standard_account")
    private boolean isStandardAccount;

    @Column(name = "directors_name")
    private String directorsName;

    @Column(name = "directors_email")
    private String directorsEmail;

    @Column(name = "directors_phone")
    private String directorsPhone;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "address")
    private String address;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CustomerHistory> customerHistories;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isFullCreated() {
        return isFullCreated;
    }

    public void setFullCreated(boolean fullCreated) {
        isFullCreated = fullCreated;
    }

    public boolean isStandardAccount() {
        return isStandardAccount;
    }

    public void setStandardAccount(boolean standardAccount) {
        isStandardAccount = standardAccount;
    }

    public List<CustomerHistory> getCustomerHistories() {
        return customerHistories;
    }

    public void setCustomerHistories(List<CustomerHistory> customerHistories) {
        this.customerHistories = customerHistories;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getDirectorsEmail() {
        return directorsEmail;
    }

    public void setDirectorsEmail(String directorsEmail) {
        this.directorsEmail = directorsEmail;
    }

    public String getDirectorsPhone() {
        return directorsPhone;
    }

    public void setDirectorsPhone(String directorsPhone) {
        this.directorsPhone = directorsPhone;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}

