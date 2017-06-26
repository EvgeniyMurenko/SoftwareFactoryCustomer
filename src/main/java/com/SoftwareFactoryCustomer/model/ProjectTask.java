package com.SoftwareFactoryCustomer.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "s_project_task")
public class ProjectTask implements Serializable {

    public ProjectTask() {
    }

    public ProjectTask(Project project, String title, String shortDescription, String status, Date startDate, Date endDate, Date reopenDate, Long workingStaffID) {
        this.project = project;
        this.title = title;
        this.shortDescription = shortDescription;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reopenDate = reopenDate;
        this.workingStaffID = workingStaffID;
    }



    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;


    @Column(name = "status")
    private String status;


    @Column(name = "start_date")
    private Date startDate;


    @Column(name = "end_date")
    private Date endDate;


    @Column(name = "reopen_date")
    private Date reopenDate;

    @Column(name = "working_staff_id")
    private Long workingStaffID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Long getWorkingStaffID() {
        return workingStaffID;
    }

    public void setWorkingStaffID(Long workingStaffID) {
        this.workingStaffID = workingStaffID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getReopenDate() {
        return reopenDate;
    }

    public void setReopenDate(Date reopenDate) {
        this.reopenDate = reopenDate;
    }

}
