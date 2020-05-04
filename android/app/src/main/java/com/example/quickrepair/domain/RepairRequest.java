package com.example.quickrepair.domain;


import java.util.GregorianCalendar;
import java.util.Objects;

public class RepairRequest implements Comparable<RepairRequest>
{
    public enum Status
    {
        REJECTED,
        CONFIRMED,
        UNCONFIRMED,
        COMPLETED
    }

    private int uid;
    private Customer customer;
    private Job job;
    private GregorianCalendar creationDate;
    private GregorianCalendar conductionDate;
    private Address address;

    private Status status;

    private String commentsFromCustomer;
    private int estimatedDuration;

    private Repair repair;

    //constructors

    /**
     * Default constructor.
     */
    public RepairRequest()
    {
        status = Status.UNCONFIRMED;
    }

    /*
     * Constructor when the Repair Request initialize from Costumer
     */
    public RepairRequest(Customer customer, Job job, GregorianCalendar creationDate, GregorianCalendar conductionDate, Address address, String commentsFromCustomer)
    {
        setCustomer(customer);
        setJob(job);
        setCreationDate(creationDate);
        setConductionDate(conductionDate);
        setAddress(address);
        setCommentsFromCustomer(commentsFromCustomer);
        status = Status.UNCONFIRMED;
    }

    public void setConductionDate(GregorianCalendar conductionDate)
    {
        if (conductionDate == null)
        {
            throw new IllegalArgumentException("Null conductionDate");
        }
        this.conductionDate = conductionDate;
    }

    public void setCreationDate(GregorianCalendar creationDate)
    {
        if (creationDate == null)
        {
            throw new IllegalArgumentException("Null creationDate");
        }

        this.creationDate = creationDate;
    }

    public void setAddress(Address address)
    {
        if (address == null)
        {
            throw new IllegalArgumentException("Null address");
        }

        this.address = address;
    }

    public void setCustomer(Customer customer)
    {
        if (customer == null)
        {
            throw new IllegalArgumentException("Null customer");
        }

        this.customer = customer;
    }

    public void setRepair(Repair repair)
    {
        if (repair == null)
        {
            throw new IllegalArgumentException("Null repair");
        }

        this.repair = repair;
    }

    public void setJob(Job job)
    {
        if (job == null)
        {
            throw new IllegalArgumentException("Null job");
        }

        this.job = job;
    }

    public void setCommentsFromCustomer(String commentsFromCustomer)
    {
        if (commentsFromCustomer == null) throw new NullPointerException("Null commentsFromCustomer");

        this.commentsFromCustomer = commentsFromCustomer;
    }

    public void setEstimatedDuration(int estimatedDuration)
    {
        if (estimatedDuration <= 0) throw new NumberFormatException("Duration can not be negative or zero.");

        this.estimatedDuration = estimatedDuration;
    }

    /**
     * Get the repair request UID.
     *
     * @return The repair request UID.
     */
    public int getUid()
    {
        return uid;
    }

    /**
     * Set the repair request UID.
     *
     * @param uid The repair request UID.
     */
    public void setUid(int uid)
    {
        this.uid = uid;
    }

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

    public boolean isConfirmed()
    {
        return this.status == Status.CONFIRMED || this.status == Status.COMPLETED;
    }

    public boolean isCompleted()
    {
        return this.status == Status.COMPLETED;
    }

    public boolean isUnconfirmed()
    {
        return this.status == Status.UNCONFIRMED;
    }

    public boolean isRejected()
    {
        return this.status == Status.REJECTED;
    }

    public void confirm(int estimatedDuration)
    {
        if (this.status == Status.CONFIRMED)
            throw new IllegalStateException("Repair request is already confirmed.");

        this.status = Status.CONFIRMED;
        setEstimatedDuration(estimatedDuration);
    }

    public void reject()
    {
        if (this.status == Status.REJECTED)
            throw new IllegalStateException("Repair request is already rejected.");

        this.status = Status.REJECTED;
    }

    public Repair complete(double quantity)
    {
        if (this.status == Status.UNCONFIRMED)
            throw new IllegalStateException("Repair request is not confirmed.");

        if (isCompleted()) throw new IllegalStateException("Repair request is already completed.");
        this.status = Status.COMPLETED;
        Repair repair = new Repair(this, quantity);
        setRepair(repair);

        return repair;
    }

    @Override
    public int compareTo(RepairRequest o)
    {
        return conductionDate.compareTo(o.conductionDate);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairRequest that = (RepairRequest) o;
        return estimatedDuration == that.estimatedDuration &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(job, that.job) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(conductionDate, that.conductionDate) &&
                Objects.equals(address, that.address) &&
                status == that.status &&
                Objects.equals(commentsFromCustomer, that.commentsFromCustomer) &&
                Objects.equals(repair, that.repair);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(creationDate, conductionDate, address, status, commentsFromCustomer, estimatedDuration);
    }
}
