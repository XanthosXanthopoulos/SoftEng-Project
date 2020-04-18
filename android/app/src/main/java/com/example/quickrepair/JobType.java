package com.example.quickrepair;

import java.util.HashSet;
import java.util.Set;

public class JobType
{
    private String name;

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
}
