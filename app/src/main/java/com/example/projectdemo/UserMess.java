package com.example.projectdemo;

public class UserMess {
    String FullName;
    String RollNo;
    String url;

    public UserMess() {
    }

    public UserMess(String fullName, String rollNo, String url) {
        FullName = fullName;
        RollNo = rollNo;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
