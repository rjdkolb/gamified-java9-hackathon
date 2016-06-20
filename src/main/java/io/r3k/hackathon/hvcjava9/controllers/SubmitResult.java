package io.r3k.hackathon.hvcjava9.controllers;

public class SubmitResult {
    boolean success;
    String message;
    String nextLink;
    public SubmitResult(boolean success, String message, String nextLink) {
        this.success = success;
        this.message = message;
        this.nextLink = nextLink;
    }

    public SubmitResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }
    
    
}
