package com.example.quickrepair;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RepairRequest implements Comparable<RepairRequest>
{
    private Customer customer;
    private PaymentType paymentType;
    private Job job;
    private GregorianCalendar creationDate;
    private GregorianCalendar conductionDate;
    private Address address;

    // 0: sendeds, 1: confirmed -1: rejected
    private int isConfirmed;

    private String commentsFromCustomer;
    private int estimatedDuration;

    private Repair repair;

    //constructors

    /**
     * Empty Contructor
     */
    public RepairRequest()
    {
    }

    /*
     * Constructor when the Repair Request initialize from Costumer
     */
    public RepairRequest(Customer customer, PaymentType paymentType, Job job, GregorianCalendar creationDate, GregorianCalendar conductionDate, Address address, String commentsFromCustomer)
    {
        setCustomer(customer);
        setPaymentType(paymentType);
        setJob(job);
        setCreationDate(creationDate);
        setConductionDate(conductionDate);
        setAddress(address);
        setCommentsFromCustomer(commentsFromCustomer);
        isConfirmed = 0;
    }


    //TODO validity checks on setters
    //SETTERS
    public void setConductionDate(GregorianCalendar conductionDate)
    {
        if(conductionDate == null){ throw new IllegalArgumentException("Null conductionDate");}
        this.conductionDate = conductionDate;
    }

    public void setCreationDate(GregorianCalendar creationDate)
    {
        if(creationDate == null){ throw new IllegalArgumentException("Null creationDate");}
        this.creationDate = creationDate;
    }

    public void setAddress(Address address)
    {
        if(address == null){ throw new IllegalArgumentException("Null address");}
        this.address = address;
    }

    public void setPaymentType(PaymentType type)
    {
        if(type == null){ throw new IllegalArgumentException("Null type");}
        this.paymentType = type;
    }

    public void setCustomer(Customer customer)
    {
        if(customer == null){ throw new IllegalArgumentException("Null customer");}
        this.customer = customer;
    }

    public void setRepair(Repair repair)
    {
        if(repair == null){ throw new IllegalArgumentException("Null repair");}
        this.repair = repair;
    }

    public void setJob(Job job)
    {
        if(job == null){ throw new IllegalArgumentException("Null job");}
        this.job = job;
    }

    public void setCommentsFromCustomer(String commentsFromCustomer)
    {
        if(commentsFromCustomer == null){ throw new IllegalArgumentException("Null commentsFromCustomer");}
        this.commentsFromCustomer = commentsFromCustomer;
    }

    public void setEstimatedDuration(int estimatedDuration)
    {
        if (estimatedDuration < 0) throw new NumberFormatException("Duration can not be negative.");
        if (estimatedDuration == 0) throw new NumberFormatException("Duration can not be zero.");
        if (estimatedDuration > 480)
            throw new NumberFormatException("Duration can not be greater tha 480 minutes.");

        this.estimatedDuration = estimatedDuration;
    }

    //GETTERS
    public GregorianCalendar getCreationDate()
    {
        return creationDate;
    }

    public GregorianCalendar getConductionDate()
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

    public int isConfirmed()
    {
        return isConfirmed;
    }

    public boolean isCompleted()
    {
        return repair != null;
    }

    public String getCommentsFromCustomer()
    {
        return commentsFromCustomer;
    }

    public int getEstimatedDuration()
    {
        return estimatedDuration;
    }

    public Job getJob()
    {
        return job;
    }

    public void confirm(int estimatedDuration)
    {
        if (isConfirmed == 1) throw new IllegalStateException("Repair request is already confirmed.");
        isConfirmed = 1;
        setEstimatedDuration(estimatedDuration);
        getJob().getTechnician().notifyWithConfirmation(this);
    }

    public Repair complete(double quantity)
    {
        if (isConfirmed == -1 ||isConfirmed == 0) throw new IllegalStateException("Repair request is not confirmed.");

        if (isCompleted()) throw new IllegalStateException("Repair request is already completed.");

        Repair repair = new Repair();

        repair.setQuantity(quantity);
        repair.setRepairRequest(this);

        setRepair(repair);
        //TODO notify customer of completion (email)

        return repair;
    }

    @Override
    public int compareTo(RepairRequest o)
    {
        return conductionDate.compareTo(o.conductionDate);
    }
}
