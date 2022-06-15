package com.issuetracker.issue_tracker.repositories;

import com.issuetracker.issue_tracker.models.Issue;
import com.issuetracker.issue_tracker.utils.SqlScripts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query(value = SqlScripts.GET_ISSUES_PER_PROJECT, nativeQuery = true)
    List<Issue> getIssuesPerProject(@Param("projectId") String projectName);

    @Transactional
    @Modifying
    @Query(value = SqlScripts.DELETE_ISSUE, nativeQuery = true)
    void deleteIssue(@Param("id") long id);
}
