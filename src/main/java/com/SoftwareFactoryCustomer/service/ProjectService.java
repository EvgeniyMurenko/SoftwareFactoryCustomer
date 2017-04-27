package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.Project;

import java.util.List;

public interface ProjectService {

    void addNewProject(Project project);

    void updateProject(Project project);

    void deleteProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);
}
