package com.SoftwareFactoryCustomer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "s_customer_info")
public class CustomerInfo {

    public CustomerInfo() {
    }

    @Id
    @Column(name = "user_id")
    private Long Id;

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


    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projects;


    public Long getUserId() {
        return Id;
    }

    public void setUserId(Long userId) {
        this.Id = Id;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public CustomerInfo(Long id, String name, String company, String phone, String email, String website, Set<Project> projects) {
        Id = id;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.projects = projects;
    }
}

