package com.issuetracker.issue_tracker.controllers;

import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.models.IssueType;
import com.issuetracker.issue_tracker.models.Project;
import com.issuetracker.issue_tracker.services.IssueService;
import com.issuetracker.issue_tracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issue-tracker")
public class IssueTrackerController {
    private static final String GET_PROJECTS = "projects/get";
    private static final String INSERT_PROJECT = "projects/save/{projectName}";
    private static final String UPDATE_PROJECT = "projects/update";
    private static final String GET_PROJECT_BY_ID = "projects/get/{projectName}";
    private static final String DELETE_PROJECT = "projects/delete";
    private static final String INSERT_ISSUE = "issues/save";
    private static final String GET_ISSUES_PER_PROJECT = "issues/get/{projectName}";
    private static final String FORWARD_ISSUE_STATUS = "/issues/forward";
    private static final String REJECT_ISSUE_STATUS = "/issues/reject";
    private static final String DELETE_ISSUE = "/issues/delete/{issueId}";
    private static final String UPDATE_ISSUE = "/issues/update";
    private static final String GET_ISSUE_OVERVIEW = "/issues/overview/{issueId}";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IssueService issueService;

    @GetMapping(GET_PROJECTS)
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @PostMapping(INSERT_PROJECT)
    public ResponseEntity<Void> createProject(@PathVariable("projectName") String projectName) {
        projectService.insertProject(projectName);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(UPDATE_PROJECT)
    public ResponseEntity<Void> updateProject(@RequestParam String currentName, @RequestParam String renamedProjectName) {
        projectService.updateProject(currentName, renamedProjectName);

        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_PROJECT_BY_ID)
    public ResponseEntity<Project> getProjectById(@PathVariable("projectName") String projectName) {
        return ResponseEntity.ok(projectService.getProjectById(projectName));
    }

    @DeleteMapping(DELETE_PROJECT)
    public ResponseEntity<Void> deleteProject(@RequestParam String projectName) {
        projectService.deleteProject(projectName);

        return ResponseEntity.ok().build();
    }

    @PostMapping(INSERT_ISSUE)
    public ResponseEntity<Void> insertIssue(
            @RequestParam String projectName,
            @RequestParam String creator,
            @RequestParam IssueType issueType,
            @RequestParam String assignee,
            @RequestParam Optional<String> description,
            @RequestParam Optional<LocalDateTime> dueDate) {
        issueService.insertIssue(projectName, creator, issueType, assignee, description, dueDate);

        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_ISSUES_PER_PROJECT)
    public ResponseEntity<List<Issue>> getIssuesPerProject(@PathVariable("projectName") String projectName) {
        return ResponseEntity.ok(issueService.getIssuesPerProject(projectName));
    }

    @PostMapping(FORWARD_ISSUE_STATUS)
    public ResponseEntity<Void> forwardIssueStatus(@RequestParam long issueId, @RequestParam String updatedBy) {
        issueService.forwardIssueStatus(issueId, updatedBy);

        return ResponseEntity.ok().build();
    }

    @PostMapping(REJECT_ISSUE_STATUS)
    public ResponseEntity<Void> rejectIssueStatus(@RequestParam long issueId, @RequestParam String updatedBy) {
        issueService.rejectIssueStatus(issueId, updatedBy);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(DELETE_ISSUE)
    public ResponseEntity<Void> deleteIssueStatus(@PathVariable("issueId") long issueId) {
        issueService.deleteIssue(issueId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(UPDATE_ISSUE)
    public ResponseEntity<Void> updateIssue(@RequestParam long issueId,
                                            @RequestParam Optional<String> assignee,
                                            @RequestParam Optional<String> description,
                                            @RequestParam Optional<LocalDateTime> dueDate) {
        issueService.updateIssue(issueId, assignee, description, dueDate);

        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_ISSUE_OVERVIEW)
    public ResponseEntity<Issue> getIssueOverview(@PathVariable("issueId") long issueId) {
        return ResponseEntity.ok(issueService.getIssueOverview(issueId));
    }
}
