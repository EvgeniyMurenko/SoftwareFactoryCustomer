package com.SoftwareFactoryCustomer.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "s_google_cloud_keys")
public class GoogleCloudKey {

    public GoogleCloudKey() {
    }

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "google_cloud_keys_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ManagerInfo managerInfo;

    @Column(name = "cloud_key")
    private String key;


    public GoogleCloudKey(ManagerInfo managerInfo, String key) {
        this.managerInfo = managerInfo;
        this.key = key;
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
