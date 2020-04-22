package com.example.quickrepair;

import java.time.LocalDateTime;

public class RepairRequest{
    //TODO replace with job slot for conductionDate
    private LocalDateTime creationDate;
    private LocalDateTime conductionDate;
    private Address address;
    private boolean isConfirmed;
    //TODO isCompleted is not needded because we cn check if the repair is null
    private boolean isCompleted;
    private Job job;
    private Repair repair;
    private Customer customer;
    private PaymentType paymentType;
    //TODO Constructor on repair request

    //constructors
    /**
    * Empty Contructor
     */
    public RepairRequest(){ }

    //TODO validity checks on setters
    //SETTERS

    public void setConductionDate(LocalDateTime conductionDate){
        this.conductionDate = conductionDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
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
    public void setRepair(Repair repair){
        this.repair = repair;
    }
    public void setJob(Job job) { this.job = job; }

    //GETTERS

    public LocalDateTime getCreationDate() { return creationDate; }

    public LocalDateTime getConductionDate() { return conductionDate; }

    public Address getAddress() { return address; }

    public Repair getRepair() { return repair; }

    public Customer getCustomer() { return customer; }

    public PaymentType getPaymentType() { return paymentType; }

    public boolean isConfirmed() { return isConfirmed; }

    public boolean isCompleted(){ return repair!=null; }

    public Job getJob() { return job; }

    public void confirm(){
        if (isConfirmed) throw new IllegalStateException("Repair request is already confirmed.");

        isConfirmed = true;
    }

    public void complete(){
        if (!isConfirmed) throw new IllegalStateException("Repair request is not confirmed.");
        if (isCompleted) throw new IllegalStateException("Repair request is already completed.");

        isCompleted = true;
    }


    public Repair conductRepair(double quantity){
        Repair repair = new Repair();

        repair.setQuantity(quantity);
        repair.setRepairRequest(this);

        setRepair(repair);

        return repair;
    }
}
