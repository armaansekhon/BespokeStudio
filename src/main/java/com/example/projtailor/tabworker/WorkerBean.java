package com.example.projtailor.tabworker;


//wname ,address,mobile,splz
public class WorkerBean {



    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSplz() {
        return splz;
    }

    public void setSplz(String splz) {
        this.splz = splz;
    }

    String wname;
    String address;
    String mobile;
    String splz;

    public WorkerBean(String wname, String address, String mobile, String splz) {
        this.wname = wname;
        this.address = address;
        this.mobile = mobile;
        this.splz = splz;
    }
}

