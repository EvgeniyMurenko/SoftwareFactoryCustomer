package com.SoftwareFactoryCustomer.comparator;

import com.SoftwareFactoryCustomer.model.Project;
import java.util.Comparator;

public class ProjectByDateComparator implements Comparator<Project> {
    @Override
    public int compare(Project project1, Project project2) {
        return -1*(project1.getCreateDate().compareTo(project2.getCreateDate()));
    }
}
