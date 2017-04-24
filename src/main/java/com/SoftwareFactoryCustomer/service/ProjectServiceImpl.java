package com.SoftwareFactoryCustomer.service;

import com.SoftwareFactoryCustomer.dao.ProjectDao;
import com.SoftwareFactoryCustomer.dao.ProjectDaoImpl;
import com.SoftwareFactoryCustomer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    @Autowired(required=true)
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    @Transactional
    public void addNewProject(Project project) {
        projectDao.create(project);
    }

    @Override
    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    @Override
    @Transactional
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

    @Override
    @Transactional
    public Project getProjectById(Long id) {
        return projectDao.read(id);
    }
}
