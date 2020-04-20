package com.example.quickrepair;

public class Job
{
    private Technician technician;
    private JobType jobType;

    private double price;
    private double duration;

    public Job() { }

    public Job(Technician technician, JobType jobType, double price, double duration)
    {
        setTechnician(technician);
        setJobType(jobType);
        setPrice(price);
        setDuration(duration);
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration)
    {
        if (price < 0) throw new NumberFormatException("Duration can not be negative.");
        if (price == 0) throw new NumberFormatException("Duration can not be zero.");
        if (price > 480) throw new NumberFormatException("Duration can not be greater tha 480 minutes.");

        this.duration = duration;
    }
}
