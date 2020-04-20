package com.example.quickrepair;

import java.time.LocalDateTime;
import java.util.Date;

public class RepairRequest
{
    private LocalDateTime creationDate;
    private LocalDateTime conductionDate;
    private Address address;
    private boolean isConfirmed;
    private Repair repair;
    private Job job;
    private Technician technician;
    private Customer customer;

    private PaymentType paymentType;

    public void setDate(LocalDateTime date)
    {
        this.conductionDate = date;
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

    public LocalDateTime getConductionDate()
    {
        return conductionDate;
    }

    public void setConductionDate(LocalDateTime conductionDate)
    {
        this.conductionDate = conductionDate;
    }

    public Job getJob()
    {
        return job;
    }

    public void setJob(Job job)
    {
        this.job = job;
    }

    public Address getAddress()
    {
        return address;
    }

    public Repair getRepair()
    {
        return repair;
    }

    public void setRepair(Repair repair)
    {
        this.repair = repair;
    }

    public Technician getTechnician()
    {
        return technician;
    }

    public void setTechnician(Technician technician)
    {
        this.technician = technician;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void confirmRequest()
    {

    }
}
