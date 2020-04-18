package com.example.quickrepair;

public class Job
{
    private Technician technician;
    private JobType jobType;

    private double price;

    public Job() { }

    public Job(Technician technician, JobType jobType, double price)
    {
        setTechnician(technician);
        setJobType(jobType);
        setPrice(price);
    }

    public Technician getTechnician()
    {
        return technician;
    }

    public void setTechnician(Technician technician)
    {
        if (technician == null) throw new NullPointerException("Technician can not be null.");

        this.technician = technician;
    }

    public JobType getJobType()
    {
        return jobType;
    }

    public void setJobType(JobType jobType)
    {
        if (jobType == null) throw new NullPointerException("JobType can not be null.");

        this.jobType = jobType;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        if (price < 0) throw new NumberFormatException("Price can not be negative.");
        if (price == 0) throw new NumberFormatException("Price can not be zero.");

        this.price = price;
    }
}
