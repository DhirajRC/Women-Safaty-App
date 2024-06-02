package com.example.demo2;

public class UserInfoHolder {
    String fullName,mobileNo,fullAdress;

    public UserInfoHolder() {

    }


    public UserInfoHolder(String fullName, String mobileNo, String fullAdress) {
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.fullAdress = fullAdress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFullAdress() {
        return fullAdress;
    }

    public void setFullAdress(String fullAdress) {
        this.fullAdress = fullAdress;
    }
}
