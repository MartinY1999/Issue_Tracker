package com.issuetracker.issue_tracker.services;

import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.models.IssueHistory;
import com.issuetracker.issue_tracker.models.IssueType;
import com.issuetracker.issue_tracker.models.Project;
import com.issuetracker.issue_tracker.repositories.IssueHistoryRepository;
import com.issuetracker.issue_tracker.repositories.IssueRepository;
import com.issuetracker.issue_tracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    IssueHistoryRepository issueHistoryRepository;

    public List<Issue> getIssuesPerProject(String projectName) {
        return issueRepository.getIssuesPerProject(projectName);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void insertIssue(String projectName,
                            String creator,
                            IssueType issueType,
                            String assignee,
                            Optional<String> description,
                            Optional<LocalDateTime> dueDate) {
        Project project = projectRepository.findById(projectName).orElseThrow(RuntimeException::new);
        Issue issue = Issue.of(creator, issueType);
        IssueHistory issueHistory = IssueHistory.of(assignee, description, dueDate);

        issue.addIssueHistory(issueHistory);
        project.addIssue(issue);

        issueRepository.save(issue);
        issueHistoryRepository.save(issueHistory);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void forwardIssueStatus(long issueId, String updatedBy) {
        IssueHistory mostRecentIssueHistory = getMostRecentIssueHistory(issueId);

        mostRecentIssueHistory.setIssueStatus(mostRecentIssueHistory.getIssueStatus().nextStatus());
        mostRecentIssueHistory.setUpdatedOn(LocalDateTime.now());
        mostRecentIssueHistory.setUpdatedBy(updatedBy);

        issueHistoryRepository.save(mostRecentIssueHistory);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void rejectIssueStatus(long issueId, String updatedBy) {
        IssueHistory mostRecentIssueHistory = getMostRecentIssueHistory(issueId);

        mostRecentIssueHistory.setIssueStatus(mostRecentIssueHistory.getIssueStatus().rejectStatus());
        mostRecentIssueHistory.setUpdatedOn(LocalDateTime.now());
        mostRecentIssueHistory.setUpdatedBy(updatedBy);

        issueHistoryRepository.save(mostRecentIssueHistory);
    }

    private IssueHistory getMostRecentIssueHistory(long issueId) {
        List<IssueHistory> issueHistories = getIssueOverview(issueId)
                .getIssueHistories();

        return issueHistories.get(issueHistories.size() - 1);
    }

    public void deleteIssue(long issueId) {
        issueRepository.deleteIssue(issueId);
    }

    public void updateIssue(long issueId, Optional<String> assignee, Optional<String> description, Optional<LocalDateTime> dueDate) {
        IssueHistory mostRecentIssueHistory = getMostRecentIssueHistory(issueId);

        if (!assignee.isEmpty()) {
            mostRecentIssueHistory.setAssignee(assignee.get());
        }

        if (!description.isEmpty()) {
            mostRecentIssueHistory.setDescription(description.get());
        }

        if (!dueDate.isEmpty()) {
            mostRecentIssueHistory.setDueDate(dueDate.get());
        }

        issueHistoryRepository.save(mostRecentIssueHistory);
    }

    public Issue getIssueOverview(long issueId) {
        return issueRepository.findById(issueId).orElseThrow(RuntimeException::new);
    }
}
