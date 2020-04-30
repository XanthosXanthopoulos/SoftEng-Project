package com.example.quickrepair.domain;

import java.util.HashSet;
import java.util.Set;

public class JobType
{
    private String name;
    private Specialty specialty;
    private MeasurementUnit measurementUnit;

    private Set<Job> jobs;

    /**
     * Empty Constructor
     */
    public JobType()
    {
        jobs = new HashSet<>();
    }

    public JobType(String name, Specialty specialty, MeasurementUnit measurementUnit)
    {
        this();
        setName(name);
        setSpecialty(specialty);
        setMeasurementUnit(measurementUnit);
    }


    //getters
    public String getName()
    {
        return name;
    }

    public Specialty getSpecialty()
    {
        return specialty;
    }

    public MeasurementUnit getMeasurementUnit()
    {
        return measurementUnit;
    }

    public Set<Job> getJobs()
    {
        return jobs;
    }

    public void setName(String name)
    {
        if (name == null) throw new NullPointerException();
        if (name.isEmpty()) throw new IllegalArgumentException();

        this.name = name;
    }

    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        this.specialty = specialty;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit)
    {
        this.measurementUnit = measurementUnit;
    }

    /**
     * Adds a job to the set of jobs that have this jobtype
     * @param job the job to be added
     */
    public void addJob(Job job)
    {
        if(job == null) throw new NullPointerException();
        jobs.add(job);
    }
    /**
     * Removes a job from the set of jobs that have this jobtype
     * @param job the job to be removed
     */
    public void removeJob(Job job)
    {
        if(job == null) throw new NullPointerException();
        jobs.remove(job);
    }

    /**
     * @return the set of technicians that offer this jobtype
     */
    public Set<Technician> getTechnicians(){
        Set<Technician> technicians = new HashSet<>();
        for(Job job : jobs){
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
