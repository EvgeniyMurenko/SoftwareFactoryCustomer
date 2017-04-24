package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.Case;

import java.util.List;

public interface CaseService {
    void addNewCase(Case aCase);
    void updateCase(Case aCase);
    void deleteCase(Case aCase);
    List<Case> getAllCases();
    Case getCaseById(Long id);
    List<Case> findByField(String title, String projectName);
    List<Case> getCasesHundredLimit();
}
