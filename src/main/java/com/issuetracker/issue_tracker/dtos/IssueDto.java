package com.issuetracker.issue_tracker.dtos;

import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.models.IssueType;

import java.time.LocalDateTime;

public class IssueDto {
    private long id;

    private LocalDateTime createdOn;

    private String creator;

    private IssueType issueType;

    public IssueDto(long id, LocalDateTime createdOn, String creator,
                    IssueType issueType) {
        this.id = id;
        this.createdOn = createdOn;
        this.creator = creator;
        this.issueType = issueType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public static IssueDto toDto(Issue issue) {
        return new IssueDto(issue.getId(), issue.getCreatedOn(), issue.getCreator(), issue.getIssueType());
    }

    public static Issue toEntity(IssueDto issueDto) {
        return new Issue(issueDto.getId(), issueDto.getCreatedOn(), issueDto.getCreator(), issueDto.getIssueType());
    }
}
