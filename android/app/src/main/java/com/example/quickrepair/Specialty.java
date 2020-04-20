package com.example.quickrepair;

import java.util.HashSet;
import java.util.Set;

public class Specialty
{
    private String name;

    private Set<JobType> jobTypes;

    public Specialty() { }

    public Specialty(String name)
    {
        setName(name);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null) throw new NullPointerException("Speciality name can not be null.");
        if (name.isEmpty()) throw new IllegalArgumentException("Speciality name can not be empty.");

        this.name = name;
    }

    public void addJobType(JobType jobType)
    {
        if (jobType == null) throw new NullPointerException("Job type can not be null.");

        jobType.setSpecialty(this);
        jobTypes.add(jobType);
    }

    public Set<JobType> getJobTypes()
    {
        return new HashSet<>(jobTypes);
    }

}
