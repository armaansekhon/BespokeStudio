package com.example.projtailor.measureExp;



//order_id int, mobile varchar,dress varchar,pic varchar,dodel date,qty int ,bill int,measurement varchar,worker varchar,doorder date,stat int

import java.util.Date;

public class MeasBean {
    int order_id;
    String mobile;
    String dress;
    String worker;



    String dodel;
//    int qty;
//    int  bill ;
    String measurement ;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getDodel() {
        return dodel;
    }

    public void setDodel(String dodel) {
        this.dodel = dodel;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }
    public MeasBean(int order_id, String mobile, String dress, Date dodel, String measurement, String worker) {
        this.order_id = order_id;
        this.mobile = mobile;
        this.dress = dress;
        this.dodel = String.valueOf(dodel);
        this.measurement = String.valueOf(measurement);
        this.worker = worker;
    }


//    Date doorder;
//    int stat;


}
