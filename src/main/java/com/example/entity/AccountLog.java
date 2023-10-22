package com.example.entity;

import java.util.Date;

public class AccountLog {

    private Long logId;
    private Long userId;
    private Date timestamp;
    private String activity;
    private String details;

    public AccountLog() {
        // Default constructor
    }

    public AccountLog(Long userId, Date timestamp, String activity, String details) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.activity = activity;
        this.details = details;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
