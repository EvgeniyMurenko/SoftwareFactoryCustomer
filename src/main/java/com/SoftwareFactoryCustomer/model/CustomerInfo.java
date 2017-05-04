package com.SoftwareFactoryCustomer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "s_customer_info")
public class CustomerInfo {

    public CustomerInfo() {
    }

    public CustomerInfo(User user, String name, String company, String phone, String email, String website, boolean isFullCreated, Set<Project> projects) {
        this.user = user;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.isFullCreated = isFullCreated;
        this.projects = projects;
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


    @Column(name="is_full_created")
    private boolean isFullCreated;


    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projects;


    @Column(name = "is_standard_account")
    private boolean isStandardAccount;


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
}

