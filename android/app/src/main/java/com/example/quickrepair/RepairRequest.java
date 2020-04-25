package com.example.quickrepair;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RepairRequest implements Comparable<RepairRequest>
{
    //TODO replace with job slot for conductionDate
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
    //TODO Constructor on repair request

    //constructors

    /**
     * Empty Contructor
     */
    public RepairRequest() { }

    /*
    * Constructor when the Repair Request initialize from Costumer
     */
    public RepairRequest(Customer customer, PaymentType paymentType, Job job,GregorianCalendar creationDate, GregorianCalendar conductionDate, Address address) {
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

    public void setCommentsFromCustomer(String commentsFromCustomer) {
        this.commentsFromCustomer = commentsFromCustomer;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        if (estimatedDuration < 0) throw new NumberFormatException("Duration can not be negative.");
        if (estimatedDuration == 0) throw new NumberFormatException("Duration can not be zero.");
        if (estimatedDuration > 480) throw new NumberFormatException("Duration can not be greater tha 480 minutes.");

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

    public String getCommentsFromCustomer() {
        return commentsFromCustomer;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
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

    @Override
    public int compareTo(RepairRequest o) {

        if(o.getConductionDate().get(o.conductionDate.YEAR) != this.getConductionDate().get(this.conductionDate.YEAR) ||
                o.getConductionDate().get(o.conductionDate.MONTH) != this.getConductionDate().get(this.conductionDate.MONTH) ||
                o.getConductionDate().get(o.conductionDate.DAY_OF_MONTH) != this.getConductionDate().get(this.conductionDate.DAY_OF_MONTH)){
            throw new IllegalStateException("only same day");
        }
        if((this.getConductionDate().get(this.conductionDate.HOUR) == o.getConductionDate().get(o.conductionDate.HOUR))
        && (this.getConductionDate().get(this.conductionDate.MINUTE) == o.getConductionDate().get(o.conductionDate.MINUTE))) {
            return 0;
        }else if(this.getConductionDate().get(this.conductionDate.HOUR) < o.getConductionDate().get(o.conductionDate.HOUR)){
            return -1;
        }else if((this.getConductionDate().get(this.conductionDate.HOUR) == o.getConductionDate().get(o.conductionDate.HOUR))
                && this.getConductionDate().get(this.conductionDate.MINUTE) < o.getConductionDate().get(o.conductionDate.MINUTE)){
            return -1;
        }
        return 1;
    }
}
