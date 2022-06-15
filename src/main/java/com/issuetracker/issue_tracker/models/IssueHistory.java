package com.issuetracker.issue_tracker.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "issues_history")
public class IssueHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    @Enumerated
    private IssueStatus issueStatus;

    @Column(updatable = false)
    private LocalDateTime updatedOn;

    @Column(updatable = false)
    private String updatedBy;

    @Column(updatable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false, updatable = false)
    private String assignee;

    @Column(updatable = false)
    private String description;

    private IssueHistory(String assignee, Optional<String> description, Optional<LocalDateTime> dueDate) {
        this.issueStatus = IssueStatus.NEW;
        this.assignee = assignee;
        this.description = description.orElse(null);
        this.dueDate = dueDate.orElse(null);
    }

    public IssueHistory() {

    }

    public static IssueHistory of(String assignee, Optional<String> description, Optional<LocalDateTime> dueDate) {
        return new IssueHistory(assignee, description, dueDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
