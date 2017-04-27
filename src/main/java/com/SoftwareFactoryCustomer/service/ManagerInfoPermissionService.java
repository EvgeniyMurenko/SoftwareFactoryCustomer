package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.ManagerInfoPermission;

import java.util.List;

public interface ManagerInfoPermissionService {

    ManagerInfoPermission findById(Long id);

    ManagerInfoPermission findByPermission(String permission);

    List<ManagerInfoPermission> findAll();

}
