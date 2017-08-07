package com.SoftwareFactoryCustomer.model;

import javax.persistence.*;
import java.io.Serializable;




@Entity
@Table(name = "s_permission")
public class Permission implements Serializable {


    public Permission (){}


    public Permission(Long id, ManagerInfo managerInfo, Boolean superAdminPermission, Boolean estimatePermission, Boolean casePermission, Boolean customerPermission, Boolean projectsPermission, Boolean staffPermission, Boolean noticePermission, Boolean permissionManagement, Boolean translatePermission) {
        this.id = id;
        this.managerInfo = managerInfo;
        this.superAdminPermission = superAdminPermission;
        this.estimatePermission = estimatePermission;
        this.casePermission = casePermission;
        this.customerPermission = customerPermission;
        this.projectsPermission = projectsPermission;
        this.staffPermission = staffPermission;
        this.noticePermission = noticePermission;
        this.permissionManagement = permissionManagement;
        this.translatePermission = translatePermission;
    }

    @Id
    @Column(name = "manager_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private ManagerInfo managerInfo;


    @Column(name = "super_admin_permission")
    private Boolean superAdminPermission;


    @Column(name = "estimate_permission")
    private Boolean estimatePermission;


    @Column(name = "case_permission")
    private Boolean casePermission;


    @Column(name = "customer_permission")
    private Boolean customerPermission;


    @Column(name = "projects_permission")
    private Boolean projectsPermission;


    @Column(name = "staff_permission")
    private Boolean staffPermission;


    @Column(name = "notice_permission")
    private Boolean noticePermission;


    @Column(name = "permission_management")
    private Boolean permissionManagement;


    @Column(name = "translate_permission")
    private Boolean translatePermission;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", managerInfo=" + managerInfo +
                ", superAdminPermission=" + superAdminPermission +
                ", estimatePermission=" + estimatePermission +
                ", casePermission=" + casePermission +
                ", customerPermission=" + customerPermission +
                ", projectsPermission=" + projectsPermission +
                ", staffPermission=" + staffPermission +
                ", noticePermission=" + noticePermission +
                ", permissionManagement=" + permissionManagement +
                ", translatePermission=" + translatePermission +
                '}';
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }

    public Boolean getSuperAdminPermission() {
        return superAdminPermission;
    }

    public void setSuperAdminPermission(Boolean superAdminPermission) {
        this.superAdminPermission = superAdminPermission;
    }

    public Boolean getEstimatePermission() {
        return estimatePermission;
    }

    public void setEstimatePermission(Boolean estimatePermission) {
        this.estimatePermission = estimatePermission;
    }

    public Boolean getCasePermission() {
        return casePermission;
    }

    public void setCasePermission(Boolean casePermission) {
        this.casePermission = casePermission;
    }

    public Boolean getCustomerPermission() {
        return customerPermission;
    }

    public void setCustomerPermission(Boolean customerPermission) {
        this.customerPermission = customerPermission;
    }

    public Boolean getProjectsPermission() {
        return projectsPermission;
    }

    public void setProjectsPermission(Boolean projectsPermission) {
        this.projectsPermission = projectsPermission;
    }

    public Boolean getStaffPermission() {
        return staffPermission;
    }

    public void setStaffPermission(Boolean staffPermission) {
        this.staffPermission = staffPermission;
    }

    public Boolean getNoticePermission() {
        return noticePermission;
    }

    public void setNoticePermission(Boolean noticePermission) {
        this.noticePermission = noticePermission;
    }

    public Boolean getPermissionManagement() {
        return permissionManagement;
    }

    public void setPermissionManagement(Boolean permissionManagement) {
        this.permissionManagement = permissionManagement;
    }

    public Boolean getTranslatePermission() {
        return translatePermission;
    }

    public void setTranslatePermission(Boolean translatePermission) {
        this.translatePermission = translatePermission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
