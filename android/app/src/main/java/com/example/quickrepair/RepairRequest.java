package com.example.quickrepair;


import java.time.LocalDateTime;
import java.util.Date;

public class RepairRequest {
    //TODO replace with job slot for conductionTime
    private LocalDateTime creationDate;
    private LocalDateTime conductionDate;
    private Address address;
    private boolean isConfirmed;
    private Repair repair;
    private Customer customer;

    private PaymentType paymentType;



    public void setDate(LocalDateTime date){
        this.conductionDate = date;
    }
    public void setAddress(Address address){
        this.address = address;
    }
    public void setPaymentType(PaymentType type){
        this.paymentType = type;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
