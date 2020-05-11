package com.example.quickrepair.dao;

import com.example.quickrepair.domain.Job;

import java.util.List;

public interface JobDAO {

    /**
     * Find all Jobs
     * @return Τα βιβλία
     */
    List<Job> findAll();

    /**
     * Save a Job
     * @param entity JobType
     */
    void save(Job entity);

    /**
     * Find the JobType with the given id
     * @param jobTypeID Ο κωδικός του βιβλίο
     * @return Το βιβλίο που βρέθηκε ή null
     */
    Job find(int jobTypeID);

    /**
     * Return the next id for a Job.
     * @return Job's id
     */
    int nextId();
}
