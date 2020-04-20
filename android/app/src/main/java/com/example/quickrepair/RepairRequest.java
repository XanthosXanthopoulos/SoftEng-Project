package com.example.quickrepair;


import java.time.LocalDateTime;
import java.util.Date;

public class RepairRequest {
    //TODO replace with job slot for conductionDate
    private LocalDateTime creationDate;
    private LocalDateTime conductionDate;
    private Address address;
    private boolean isConfirmed;
    private Repair repair;
    private Customer customer;

    private PaymentType paymentType;


    //TODO validity checks on setters

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

    //GETTERS

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getConductionDate() {
        return conductionDate;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public Repair getRepair() {
        return repair;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
