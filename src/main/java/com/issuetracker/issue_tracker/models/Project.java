package com.issuetracker.issue_tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    private String name;

    @Column(nullable = false, name = "del_flag")
    private Boolean deleteFlag = false;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "project_id", referencedColumnName = "name")
    private List<Issue> issues = new ArrayList<>();

    private Project(String name) {
        this.name = name;
        this.issues = Collections.emptyList();
    }

    public Project() {

    }

    public static Project of(String name) {
        return new Project(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public void addIssue(Issue issue) {
        issues.add(issue);
    }
}
