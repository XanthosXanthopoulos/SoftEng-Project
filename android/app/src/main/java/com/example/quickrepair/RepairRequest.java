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
    private boolean isConfirmed;

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
    public RepairRequest(Customer customer, PaymentType paymentType, Job job, GregorianCalendar creationDate, GregorianCalendar conductionDate, Address address)
    {
        //TODO Change gregorian to calendar
        //TODO String comments in input
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
    public void setConductionDate(GregorianCalendar conductionDate)
    {
        this.conductionDate = conductionDate;
    }

    public void setCreationDate(GregorianCalendar creationDate)
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

    public void setCommentsFromCustomer(String commentsFromCustomer)
    {
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

    public boolean isConfirmed()
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
        if (isConfirmed) throw new IllegalStateException("Repair request is already confirmed.");
        isConfirmed = true;
        setEstimatedDuration(estimatedDuration);
        getJob().getTechnician().notifyWithConfirmation(this);
    }

    public Repair complete(double quantity)
    {
        if (!isConfirmed) throw new IllegalStateException("Repair request is not confirmed.");

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
