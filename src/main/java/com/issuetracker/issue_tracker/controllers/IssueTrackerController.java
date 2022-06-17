package com.issuetracker.issue_tracker.controllers;

import com.issuetracker.issue_tracker.dtos.IssueDto;
import com.issuetracker.issue_tracker.dtos.IssueInfoDto;
import com.issuetracker.issue_tracker.dtos.ProjectDto;
import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.models.IssueHistory;
import com.issuetracker.issue_tracker.models.IssueType;
import com.issuetracker.issue_tracker.models.Project;
import com.issuetracker.issue_tracker.services.IssueService;
import com.issuetracker.issue_tracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<Project> responseBody = projectService.getProjects();
        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responseBody.stream()
            .map(ProjectDto::toDto)
            .collect(Collectors.toList()));
    }

    @PostMapping(INSERT_PROJECT)
    public ResponseEntity<Void> createProject(@PathVariable("projectName") String projectName) {
        projectService.insertProject(projectName);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(UPDATE_PROJECT)
    public ResponseEntity<Void> updateProject(@RequestParam String currentName, @RequestParam String renamedProjectName) {
        projectService.updateProject(currentName, renamedProjectName);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(GET_PROJECT_BY_ID)
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("projectName") String projectName) {
        Project responseBody = projectService.getProjectById(projectName);
        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ProjectDto.toDto(responseBody));
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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(GET_ISSUES_PER_PROJECT)
    public ResponseEntity<List<IssueDto>> getIssuesPerProject(@PathVariable("projectName") String projectName) {
        List<Issue> responseBodyIssues = issueService.getIssuesPerProject(projectName);
        if (responseBodyIssues == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responseBodyIssues.stream()
            .map(IssueDto::toDto)
            .collect(Collectors.toList()));
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
    public ResponseEntity<IssueDto> getIssueOverview(@PathVariable("issueId") long issueId) {
        Issue responseBody = issueService.getIssueOverview(issueId);
        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(IssueDto.toDto(responseBody));
//        ResponseEntity<IssueInfoDto> responseBody;
//        Issue issue = issueService.getIssueOverview(issueId);
//        IssueHistory history = issueService.getLastIssueHistoryByIssueId(issueId);
//        responseBody = ResponseEntity.ok(new IssueInfoDto(issue, history));
//        return responseBody;
    }
}
