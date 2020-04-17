package com.example.quickrepair;

import android.location.Address;

import java.util.Date;

public class RepairRequest {
    private Date creationDate;
    private Date conductionDate;
    private String address;
    private boolean isConfirmed;

    private PaymentType paymentType;

    public void setDate(Date date){
        this.conductionDate = date;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPaymentType(PaymentType type){
        this.paymentType = type;
    }
}
