package com.issuetracker.issue_tracker.models;

public enum IssueStatus {
    NEW {
        @Override
        public IssueStatus nextStatus() {
            return STARTED;
        }

        @Override
        public IssueStatus rejectStatus() {
            return REJECTED;
        }
    }, STARTED {
        @Override
        public IssueStatus nextStatus() {
            return COMPLETED;
        }

        @Override
        public IssueStatus rejectStatus() {
            return REJECTED;
        }
    }, COMPLETED {
        @Override
        public IssueStatus nextStatus() {
            throw new RuntimeException("Cannot complete an issue again!");
        }

        @Override
        public IssueStatus rejectStatus() {
            throw new RuntimeException("Cannot reject a completed issue!");
        }
    }, REJECTED {
        @Override
        public IssueStatus nextStatus() {
            return STARTED;
        }

        @Override
        public IssueStatus rejectStatus() {
            throw new RuntimeException("Cannot reject an issue again!");
        }
    };

    public abstract IssueStatus nextStatus();
    public abstract IssueStatus rejectStatus();
}
