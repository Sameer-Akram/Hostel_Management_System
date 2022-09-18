package com.example.projectdemo;

import android.widget.TextView;

public class User {


    String Name;

    public User(String name, String email, String mobile, String bloodGroup, String category, String gender, String rollNo, String localGuardian, String class1, String year, String branch, String CNIC, String permAdd) {
        Name = name;
        Email = email;
        Mobile = mobile;
        BloodGroup = bloodGroup;
        Category = category;
        Gender = gender;
        RollNo = rollNo;
        LocalGuardian = localGuardian;
        Class1 = class1;
        Year = year;
        Branch = branch;
        this.CNIC = CNIC;
        PermAdd = permAdd;
    }

    String Email;
    String Mobile;
    String BloodGroup;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getLocalGuardian() {
        return LocalGuardian;
    }

    public void setLocalGuardian(String localGuardian) {
        LocalGuardian = localGuardian;
    }

    public String getClass1() {
        return Class1;
    }

    public void setClass1(String class1) {
        Class1 = class1;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getPermAdd() {
        return PermAdd;
    }

    public void setPermAdd(String permAdd) {
        PermAdd = permAdd;
    }

    String Category;
    String Gender;
    String RollNo;
    String LocalGuardian;
    String Class1;
    String Year;
    String Branch;
    String CNIC;
    String PermAdd;

    public User() {
    }



}
