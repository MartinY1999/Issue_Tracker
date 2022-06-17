package com.issuetracker.issue_tracker.dtos;

import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.models.IssueHistory;
import com.issuetracker.issue_tracker.models.IssueStatus;
import com.issuetracker.issue_tracker.models.IssueType;

import java.time.LocalDateTime;

public class IssueInfoDto {
    private long id;

    private LocalDateTime createdOn;

    private String creator;

    private IssueType issueType;

    private IssueStatus issueStatus;

    private Boolean deleteFlag = false;

    private LocalDateTime lastUpdatedOn;

    private String updatedBy;

    private LocalDateTime dueDate;

    private String assignee;

    private String description;

    public IssueInfoDto(Issue issue, IssueHistory issueHistory) {
        id = issue.getId();
        createdOn = issue.getCreatedOn();
        creator = issue.getCreator();
        issueType = issue.getIssueType();
        issueStatus = issueHistory.getIssueStatus();
        lastUpdatedOn = issueHistory.getUpdatedOn();
        updatedBy = issueHistory.getUpdatedBy();
        dueDate = issueHistory.getDueDate();
        assignee = issueHistory.getAssignee();
        description = issueHistory.getDescription();
    }


}
