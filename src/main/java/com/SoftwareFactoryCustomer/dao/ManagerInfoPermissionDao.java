package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.ManagerInfoPermission;

import java.util.List;

public interface ManagerInfoPermissionDao {

    List<ManagerInfoPermission> findAll();

    ManagerInfoPermission findByPermission(String permission);

    ManagerInfoPermission findById(Long id);

}
