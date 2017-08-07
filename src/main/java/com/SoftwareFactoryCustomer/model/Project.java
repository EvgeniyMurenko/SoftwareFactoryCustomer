package com.SoftwareFactoryCustomer.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static org.hibernate.annotations.FetchMode.SUBSELECT;

@Entity
@Table(name = "s_projects")
public class Project implements Serializable {

    public Project() {
    }

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "project_id")
    private Long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "date_create")
    private Date createDate;

    @Column(name = "status")
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_customer_id")
    private CustomerInfo customerInfo;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Case> cases;

    @Column(name = "technology_type")
    private String technologyType;

    @Column(name = "date_start")
    private Date startDate;

    @Column(name = "date_end")
    private Date endDate;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_manager_id")
    private ManagerInfo managerInfo;

    @Column(name = "pm_name")
    private String pmName;

    @Column(name = "pm__email")
    private String pmEmail;

    @Column(name = "pm_phone")
    private String pmPhone;



    @Column(name = "scenario_uuid_name")
    private String scenarioUuidName;


    public Project(String projectName, Date createDate, String status, CustomerInfo customerInfo, Set<Case> cases, String technologyType, Date startDate, Date endDate, String description, ManagerInfo managerInfo, String pmName, String pmEmail, String pmPhone) {
        this.projectName = projectName;
        this.createDate = createDate;
        this.status = status;
        this.customerInfo = customerInfo;
        this.cases = cases;
        this.technologyType = technologyType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.managerInfo = managerInfo;
        this.pmName = pmName;
        this.pmEmail = pmEmail;
        this.pmPhone = pmPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Set<Case> getCases() {
        return cases;
    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmEmail() {
        return pmEmail;
    }

    public void setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
    }

    public String getPmPhone() {
        return pmPhone;
    }

    public void setPmPhone(String pmPhone) {
        this.pmPhone = pmPhone;
    }

    public String getScenarioUuidName() {
        return scenarioUuidName;
    }

    public void setScenarioUuidName(String scenarioUuidName) {
        this.scenarioUuidName = scenarioUuidName;
    }


}
