package io.r3k.hackathon.hvcjava9.domain;

import org.springframework.data.annotation.Id;

public class RegisterRequest {
    @Id
    private String userID;

    public RegisterRequest() {
    }

    public RegisterRequest(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
