package com.intelligentsia.isms.model;

public class SmsResponse {
    private String status;
    private String message;
    private int Response;

    // Constructeurs, getters, setters
    public SmsResponse() {}

    public SmsResponse(String status, String message, int Response) {
        this.status = status;
        this.message = message;
        this.Response = Response;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResponse() {
        return Response;
    }

    public void setResponse(int response) {
        Response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
