package com.issuetracker.issue_tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false, updatable = false)
    private String creator;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Column(nullable = false, name = "del_flag")
    private Boolean deleteFlag = false;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    @JsonIgnore
    private List<IssueHistory> issueHistories = new ArrayList<>();

    private Issue(String creator, IssueType issueType) {
        this.createdOn = LocalDateTime.now();
        this.creator = creator;
        this.issueType = issueType;
    }

    public Issue(long id, LocalDateTime createdOn, String creator,
                 IssueType issueType) {
        this.id = id;
        this.createdOn = createdOn;
        this.creator = creator;
        this.issueType = issueType;
    }

    public Issue() {

    }

    public static Issue of(String creator, IssueType issueType) {
        return new Issue(creator, issueType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public List<IssueHistory> getIssueHistories() {
        return issueHistories;
    }

    public void setIssueHistories(List<IssueHistory> issueHistories) {
        this.issueHistories = issueHistories;
    }

    public void addIssueHistory(IssueHistory issueHistory) {
        issueHistories.add(issueHistory);
    }
}
