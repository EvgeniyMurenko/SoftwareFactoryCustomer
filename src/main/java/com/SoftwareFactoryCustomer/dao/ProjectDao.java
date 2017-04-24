package com.SoftwareFactoryCustomer.dao;

import com.SoftwareFactoryCustomer.model.Project;
import java.util.List;

public interface ProjectDao {
    public Long create(Project project);
    Project read(Long id);
    void update(Project project);
    void delete(Project project);
    List<Project> findAll();
}
