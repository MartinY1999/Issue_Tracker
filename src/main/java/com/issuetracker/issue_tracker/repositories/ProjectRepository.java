package com.issuetracker.issue_tracker.repositories;

import com.issuetracker.issue_tracker.models.Project;
import com.issuetracker.issue_tracker.utils.SqlScripts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    @Transactional
    @Modifying
    @Query(value = SqlScripts.UPDATE_PROJECT_NAME, nativeQuery = true)
    void updateProjectName(@Param("renamedName") String renamedName, @Param("currentName") String currentName);

    @Transactional
    @Modifying
    @Query(value = SqlScripts.DELETE_PROJECT, nativeQuery = true)
    void deleteProject(@Param("name") String name);
}
