package com.issuetracker.issue_tracker.repositories;

import com.issuetracker.issue_tracker.models.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {
}
