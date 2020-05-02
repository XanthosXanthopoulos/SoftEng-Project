package com.example.quickrepair.domain;

import java.util.HashSet;
import java.util.Set;

public class JobType
{
    private Integer uid;
    private String name;
    private Specialty specialty;
    private MeasurementUnit measurementUnit;

    private Set<Job> jobs;

    /**
     * Default constructor.
     */
    public JobType()
    {
        jobs = new HashSet<>();
    }

    /**
     * Create o job type.
     *
     * @param name            The name of the job type.
     * @param specialty       The speciality associate with the job type.
     * @param measurementUnit The measurement unit of the job type.
     */
    public JobType(String name, Specialty specialty, MeasurementUnit measurementUnit)
    {
        this();
        setName(name);
        setSpecialty(specialty);
        setMeasurementUnit(measurementUnit);
    }

    /**
     * Get the name of the job type.
     *
     * @return The name of the job type.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the associated speciality of the job type.
     *
     * @return The associated speciality.
     */
    public Specialty getSpecialty()
    {
        return specialty;
    }

    /**
     * Get the measurement unit of the job type.
     *
     * @return The measurement unit of the job type.
     */
    public MeasurementUnit getMeasurementUnit()
    {
        return measurementUnit;
    }

    /**
     * Get the set of associated jobs.
     *
     * @return The set of associated jobs.
     */
    public Set<Job> getJobs()
    {
        return new HashSet<>(jobs);
    }

    /**
     * Set the name of the job type.
     *
     * @param name The name of the job type.
     */
    public void setName(String name)
    {
        if (name == null) throw new NullPointerException();
        if (name.isEmpty()) throw new IllegalArgumentException();

        this.name = name;
    }

    /**
     * Set the associated speciality of the job type.
     *
     * @param specialty The associated speciality with the job type.
     */
    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        this.specialty = specialty;
    }

    /**
     * Set the measurement unit of the job type.
     *
     * @param measurementUnit The measurement unit of the job type.
     */
    public void setMeasurementUnit(MeasurementUnit measurementUnit)
    {
        this.measurementUnit = measurementUnit;
    }

    /**
     * Get the job type UID.
     *
     * @return The job type UID.
     */
    public Integer getUid()
    {
        return uid;
    }

    /**
     * Set the job type UID.
     *
     * @param uid The jop type UID.
     */
    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    /**
     * Adds a job to the set of jobs that have this job type.
     *
     * @param job The job to be added.
     */
    public void addJob(Job job)
    {
        if (job == null) throw new NullPointerException();
        jobs.add(job);
    }

    /**
     * Removes a job from the set of jobs that have this job type.
     *
     * @param job The job to be removed.
     */
    public void removeJob(Job job)
    {
        if (job == null) throw new NullPointerException();
        jobs.remove(job);
    }

    /**
     * @return the set of technicians that offer this job type
     */
    public Set<Technician> getTechnicians()
    {
        Set<Technician> technicians = new HashSet<>();
        for (Job job : jobs)
        {
            technicians.add(job.getTechnician());
        }
        return technicians;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;

        if (this == other) return true;

        if (!(other instanceof JobType)) return false;

        if (!name.equals(((JobType) other).getName())) return false;

        if (!specialty.equals(((JobType) other).specialty)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        if (name == null || specialty == null || specialty.getName() == null) return 0;

        String hash = name + specialty.getName();

        return hash.hashCode();
    }
}
