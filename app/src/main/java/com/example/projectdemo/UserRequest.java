package com.example.projectdemo;

public class UserRequest {


    String FullName;
    String RollNo;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserRequest() {
    }

    public UserRequest(String fullName, String rollNo,String url1) {
        FullName = fullName;
        RollNo = rollNo;
        url=url1;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }
}
