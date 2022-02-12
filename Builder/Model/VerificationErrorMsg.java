package com.jiantech.SearchQueryForSQL.Builder.Model;

public class VerificationErrorMsg {
    private boolean status = false;
    private String message;

    public VerificationErrorMsg(boolean status){
        this.status = status;
        if(status) this.message = "Passed";
    }

    public VerificationErrorMsg(boolean status, String message){
        this.status = status;
        if(status) this.message = "Passed";
        else this.message = message;
    }

    public boolean getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

}
