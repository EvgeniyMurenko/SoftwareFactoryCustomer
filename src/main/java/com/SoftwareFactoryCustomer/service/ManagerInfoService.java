package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.ManagerInfo;

import java.util.List;

public interface ManagerInfoService {

    void addNewManagerInfo(ManagerInfo managerInfo);

    void updateManagerInfo(ManagerInfo managerInfo);

    void deleteManagerInfo(ManagerInfo managerInfo);

    List<ManagerInfo> getAllManagerInfos();

    ManagerInfo getManagerInfoById(Long id);
}
