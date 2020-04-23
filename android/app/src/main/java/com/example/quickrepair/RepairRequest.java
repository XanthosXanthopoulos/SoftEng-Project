package com.example.quickrepair;

import java.util.Calendar;

public class RepairRequest
{
    //TODO replace with job slot for conductionDate
    private Customer customer;
    private PaymentType paymentType;
    private Job job;
    private Calendar creationDate;
    private Calendar conductionDate;
    private Address address;
    private boolean isConfirmed;

    private Repair repair;
    //TODO Constructor on repair request

    //constructors

    /**
     * Empty Contructor
     */
    public RepairRequest() { }

    /*
    * Constructor when the Repair Request initialize from Costumer
     */
    public RepairRequest(Customer customer, PaymentType paymentType, Job job,Calendar creationDate, Calendar conductionDate, Address address) {
        setCustomer(customer);
        setPaymentType(paymentType);
        setJob(job);
        setCreationDate(creationDate);
        setConductionDate(conductionDate);
        setAddress(address);
        isConfirmed = false;
    }


    //TODO validity checks on setters
    //SETTERS
    public void setConductionDate(Calendar conductionDate)
    {
        this.conductionDate = conductionDate;
    }

    public void setCreationDate(Calendar creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public void setPaymentType(PaymentType type)
    {
        this.paymentType = type;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setRepair(Repair repair)
    {
        this.repair = repair;
    }

    public void setJob(Job job)
    {
        this.job = job;
    }

    //GETTERS

    public Calendar getCreationDate()
    {
        return creationDate;
    }

    public Calendar getConductionDate()
    {
        return conductionDate;
    }

    public Address getAddress()
    {
        return address;
    }

    public Repair getRepair()
    {
        return repair;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public PaymentType getPaymentType()
    {
        return paymentType;
    }

    public boolean isConfirmed()
    {
        return isConfirmed;
    }

    public boolean isCompleted()
    {
        return repair != null;
    }

    public Job getJob()
    {
        return job;
    }

    public void confirm()
    {
        if (isConfirmed) throw new IllegalStateException("Repair request is already confirmed.");

        isConfirmed = true;
    }

    public void complete(Repair repair)
    {
        if (!isConfirmed) throw new IllegalStateException("Repair request is not confirmed.");
        if (this.repair!=null) throw new IllegalStateException("Repair request is already completed.");

        this.repair = repair;
    }


    public Repair conductRepair(double quantity)
    {
        Repair repair = new Repair();

        repair.setQuantity(quantity);
        repair.setRepairRequest(this);

        setRepair(repair);

        return repair;
    }
}
