package com.SoftwareFactoryCustomer.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_permission")
public class ManagerInfoPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "permission", length = 15, unique = true, nullable = false)
    private String permission = Permission.ADMIN_PERMISSION.getPermission();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerInfoPermission that = (ManagerInfoPermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return permission != null ? permission.equals(that.permission) : that.permission == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission [id=" + id + ", permission=" + permission + "]";
    }


}
