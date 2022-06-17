package com.issuetracker.issue_tracker.services;

import com.issuetracker.issue_tracker.models.Project;
import com.issuetracker.issue_tracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjects() {
        return projectRepository
                .findAll()
                .stream()
                .filter(project -> true != project.getDeleteFlag()).collect(Collectors.toList());
    }

    public void insertProject(String projectName) {
        projectRepository.save(Project.of(projectName));
    }

    public void updateProject(String currentName, String renamedProjectName) {
        projectRepository.updateProjectName(renamedProjectName, currentName);
    }

    public Project getProjectById(String projectName) {
        return projectRepository
                .findById(projectName)
                .orElse(null);
    }

    public void deleteProject(String projectName) {
        projectRepository.deleteProject(projectName);
    }
}
