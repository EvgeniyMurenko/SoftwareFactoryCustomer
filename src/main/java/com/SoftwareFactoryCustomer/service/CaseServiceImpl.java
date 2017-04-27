package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.dao.CaseDao;
import com.SoftwareFactoryCustomer.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("caseService")
public class CaseServiceImpl implements CaseService {

    private CaseDao caseDao;

    @Autowired(required = true)
    public void setCaseDao(CaseDao caseDao) {
        this.caseDao = caseDao;
    }

    @Override
    @Transactional
    public void addNewCase(Case aCase) {
        caseDao.create(aCase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Case> getAllCases() {
        return caseDao.findAll();
    }

    @Override
    @Transactional
    public Case getCaseById(Long id) {
        Case aCase = caseDao.read(id);
        return aCase;
    }

    @Override
    @Transactional
    public List<Case> findByField(String title, String projectName) {
        ArrayList<Case> cases = new ArrayList<>();
        if (!title.equals("")){
            cases.addAll(caseDao.findByTitle(title));
        }
        if (!projectName.equals("")) {
            cases.addAll(caseDao.findByProjectName(projectName));
        }
        return cases ;
    }

    @Override
    @Transactional
    public void updateCase(Case aCase) {
        caseDao.update(aCase);
    }

    @Override
    @Transactional
    public void deleteCase(Case aCase) {
        caseDao.delete(aCase);
    }

    @Override
    @Transactional
    public List<Case> getCasesHundredLimit(){
        return caseDao.findCasesHundredLimit();
    }


}
