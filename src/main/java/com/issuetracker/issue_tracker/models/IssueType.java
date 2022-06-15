package com.issuetracker.issue_tracker.models;

public enum IssueType {
    BUG("Bug"), EPIC("Epic"), STORY("Story"), TASK("Task");

    private String issueType;

    IssueType(String issueType) {
        this.issueType = issueType;
    }
}
