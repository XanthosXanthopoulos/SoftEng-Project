package com.example.quickrepair.dao;

import com.example.quickrepair.domain.Job;

import java.util.List;

public interface JobDAO {

    /**
     * Find all Jobs
     * @return Jobs
     */
    List<Job> findAll();

    /**
     * Save a Job
     * @param entity Job
     */
    void save(Job entity);

    /**
     * Find the Job with the given id
     * @param jobID job's id
     * @return Job or null
     */
    Job find(int jobID);

    /**
     * Return the next id for a Job.
     * @return Job's id
     */
    int nextId();
}
