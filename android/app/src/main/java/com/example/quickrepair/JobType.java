package com.example.quickrepair;

import java.util.HashSet;
import java.util.Set;

public class JobType
{
    private String name;
    private Specialty specialty;
    private MeasurementUnit measurementUnit;

    private Set<Job> jobs;


    //Constructor
    /**
    * Empty Constructor
     */
    public JobType() { jobs = new HashSet<>(); }

    public JobType(String name, Specialty specialty, MeasurementUnit measurementUnit) {
        setName(name);
        setSpecialty(specialty);
        setMeasurementUnit(measurementUnit);
    }
    public JobType(String name, Specialty specialty, MeasurementUnit measurementUnit, Set<Job> jobs) {
        setName(name);
        setSpecialty(specialty);
        setMeasurementUnit(measurementUnit);
        setJobs(jobs);
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

    public Set<Job> getJobs() { return jobs; }

    //setters
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

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job){
        jobs.add(job);
    }
}
