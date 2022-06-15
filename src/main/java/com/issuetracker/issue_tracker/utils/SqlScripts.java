package com.issuetracker.issue_tracker.utils;

public class SqlScripts {
    private SqlScripts() {

    }

    public static final String UPDATE_PROJECT_NAME = "UPDATE projects SET name = :renamedName WHERE name = :currentName AND del_flag = FALSE";
    public static final String DELETE_PROJECT = "UPDATE projects SET del_flag = TRUE WHERE name = :name";

    public static final String GET_ISSUES_PER_PROJECT = "SELECT id, created_on, creator, issue_type FROM issues WHERE project_id = :projectId AND del_flag = FALSE";
    public static final String DELETE_ISSUE = "UPDATE issues SET del_flag = TRUE WHERE id = :id";
}
