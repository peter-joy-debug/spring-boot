package com.example.authentication;

public class ClaimsResponse {
    private String subject;
    private String userID;
    private String userRole;

    public ClaimsResponse(String subject, String userID, String userRole) {
        this.subject = subject;
        this.userID = userID;
        this.userRole = userRole;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
