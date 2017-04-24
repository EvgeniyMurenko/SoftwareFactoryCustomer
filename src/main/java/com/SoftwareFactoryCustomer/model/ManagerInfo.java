package com.SoftwareFactoryCustomer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_manager_info")
public class ManagerInfo {

    public ManagerInfo(){}

    @Id
    @Column(name = "user_id")
    private Long Id;

    @Column(name = "name")
    private String name;



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

    public ManagerInfo(Long id, String name) {
        Id = id;
        this.name = name;
    }
}
