package com.example.projectdemo;

public class UserRoom {

    String FullName;
    String RollNo;
    String url;
    String RoomNo;
    String RoomB;

    public UserRoom() {
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

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getRoomB() {
        return RoomB;
    }

    public void setRoomB(String roomB) {
        RoomB = roomB;
    }

    public UserRoom(String fullName, String rollNo, String url, String roomNo, String roomB) {
        FullName = fullName;
        RollNo = rollNo;
        this.url = url;
        RoomNo = roomNo;
        RoomB = roomB;
    }
}
