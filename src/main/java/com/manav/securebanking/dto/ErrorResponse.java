package com.manav.securebanking.dto;

public class ErrorResponse {

    private int status;
    private String message;
    private String timestamp;

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public void setMessage(String message){
        this.message = message;

    }

    public String getMessage(){
        return message;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getTimestamp(){
        return timestamp;
    }

}
