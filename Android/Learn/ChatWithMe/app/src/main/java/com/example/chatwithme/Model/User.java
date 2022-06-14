package com.example.chatwithme.Model;

public class User {

    private String userId;
    private String username;
    private String email;
    private String password;
    private String profilePic;
    private String lastMessage;
    private String status;

    public User() {
    }

    public User(String userId, String username, String email, String password, String profilePic, String lastMessage, String status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.lastMessage = lastMessage;
        this.status = status;
    }

    public User(String username, String email, String password, String status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
