package com.issuetracker.issue_tracker.dtos;

import com.issuetracker.issue_tracker.models.Project;

public class ProjectDto {
    private String name;

    public ProjectDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ProjectDto toDto(Project project) {
        return new ProjectDto(project.getName());
    }

    public static Project toEntity(ProjectDto projectDto) {
        return new Project(projectDto.getName());
    }
}
