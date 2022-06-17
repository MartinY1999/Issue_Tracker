package com.issuetracker.issue_tracker.repositories;

import com.issuetracker.issue_tracker.models.IssueHistory;
import com.issuetracker.issue_tracker.utils.SqlScripts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {
    @Query(value = SqlScripts.GET_ISSUE_HISTORY_BY_ISSUE_ID, nativeQuery = true)
    List<IssueHistory> getIssueHistoryByIssueId(@Param("issue_id") long issueId);
}
