package com.example.quickrepair;

import java.util.HashSet;
import java.util.Set;

public class JobType
{
    private String name;
    private Specialty specialty;

    private Set<Job> jobs;

    public JobType()
    {
        jobs = new HashSet<>();
    }

    public JobType(String name)
    {
        setName(name);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null) throw new NullPointerException();
        if (name.isEmpty()) throw new IllegalArgumentException();

        this.name = name;
    }


    public Specialty getSpecialty()
    {
        return specialty;
    }

    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        this.specialty = specialty;
    }
}
