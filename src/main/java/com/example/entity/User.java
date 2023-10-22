package com.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private String country;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus accountStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<AccountLog> accountLogs;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserSession> userSessions;

    // Constructors, getters, and setters

    // Constructors

    public User() {
    }

    public User(String firstname, String lastname, String username, String email, String password, String telephone, String address, String country, UserType userType, UserStatus accountStatus, List<AccountLog> accountLogs,List<UserSession> userSessions) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
        this.country = country;
        this.userType = userType;
        this.accountStatus = accountStatus;
        this.accountLogs = accountLogs;
        this.userSessions = userSessions;
    }

    // Getters and setters for all fields

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(UserStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<AccountLog> getAccountLogs() {
        return accountLogs;
    }

    public void setAccountLogs(List<AccountLog> accountLogs) {
        this.accountLogs = accountLogs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(List<UserSession> userSessions) {
        this.userSessions = userSessions;
    }

    public UserSession getSessionByToken(String tokenValue)
    {
        UserSession session = new UserSession();
        int count = 0;
        for(int i=0; i<this.getUserSessions().size();i++)
        {
            if(this.getUserSessions().get(i).getAuthToken().equals(tokenValue))
            {
                session = this.getUserSessions().get(i);
                count++;
                break;
            }
        }
        if(count==1)
        {
            return session;
        }
        else {
            return null;
        }

    }

}
