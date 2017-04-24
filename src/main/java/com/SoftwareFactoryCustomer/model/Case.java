package com.SoftwareFactoryCustomer.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "s_cases")
public class Case {

    public Case() {
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "case_id")
    private Long id;

    @Column(name = "user_manager_id")
    private Long userManagerId;

    @Column(name = "title")
    private String projectTitle;

    @Column(name = "status")
    private String status;

    @Column(name = "date_create", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(mappedBy = "aCase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Message> messages;

    @Column(name="language")
    private String language;

    @Column(name = "emergency")
    private boolean emergency;

    @Column(name = "appointment_time")
    private Date appointmentTime;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserManagerId() {
        return userManagerId;
    }

    public void setUserManagerId(Long userManagerId) {
        this.userManagerId = userManagerId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Case(Project project, Long userManagerId, String projectTitle, String status, Date creationDate, Set<Message> messages, String language, boolean emergency, Date appointmentTime) {
        this.project = project;
        this.userManagerId = userManagerId;
        this.projectTitle = projectTitle;
        this.status = status;
        this.creationDate = creationDate;
        this.messages = messages;
        this.language = language;
        this.emergency = emergency;
        this.appointmentTime = appointmentTime;
    }

/*@Override
    public String toString() {
        return "Case{" +
                "project=" + project +
                ", id=" + id +
                ", userManagerId=" + userManagerId +
                ", projectTitle='" + projectTitle + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", messages=" + messages +
                '}';
    }*/
}
