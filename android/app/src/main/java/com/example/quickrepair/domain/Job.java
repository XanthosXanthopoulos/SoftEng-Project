package com.example.quickrepair.domain;

public class Job
{
    private Integer uid;
    private Technician technician;
    private JobType jobType;

    private double price;

    /**
     * Default constructor.
     */
    public Job() { }

    /**
     * Construct a job.
     *
     * @param technician The technician offering the job.
     * @param jobType The type of the job.
     * @param price The price of the job.
     */
    public Job(Technician technician, JobType jobType, double price)
    {
        setTechnician(technician);
        setJobType(jobType);
        setPrice(price);
    }

    /**
     * Get the technician offering the job.
     *
     * @return The technician offering the job.
     */
    public Technician getTechnician()
    {
        return technician;
    }

    /**
     * Set the technician offering the job.
     *
     * @param technician The technician offering
     */
    public void setTechnician(Technician technician)
    {
        if (technician == null) throw new NullPointerException("Technician can not be null.");

        this.technician = technician;
    }

    /**
     * Get the type of the job.
     *
     * @return The type of the job.
     */
    public JobType getJobType()
    {
        return jobType;
    }

    /**
     * Set the type of the job.
     *
     * @param jobType The type of the job.
     */
    public void setJobType(JobType jobType)
    {
        if (jobType == null) throw new NullPointerException("JobType can not be null.");

        this.jobType = jobType;
    }

    /**
     * Get the price of the job.
     *
     * @return The price of the job.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Set the price for the job.
     *
     * @param price The price of the job.
     */
    public void setPrice(double price)
    {
        if (price < 0) throw new NumberFormatException("Price can not be negative.");
        if (price == 0) throw new NumberFormatException("Price can not be zero.");

        this.price = price;
    }

    /**
     * Get the job UID.
     *
     * @return The UID.
     */
    public Integer getUid()
    {
        return uid;
    }

    /**
     * Set the job UID.
     *
     * @param uid The evaluation's UID.
     */
    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;

        if (this == other) return true;

        if (!(other instanceof Job)) return false;

        if (!jobType.equals(((Job) other).jobType)) return false;

        if (!technician.equals(((Job) other).technician)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        if (jobType == null || technician == null || jobType.getName() == null || technician.getAFM() == null)
            return 0;

        String hash = jobType.getName() + technician.getAFM();

        return hash.hashCode();
    }
}
