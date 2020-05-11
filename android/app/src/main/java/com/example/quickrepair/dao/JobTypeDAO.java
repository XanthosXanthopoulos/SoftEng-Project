package com.example.quickrepair.dao;

import com.example.quickrepair.domain.JobType;

import java.util.List;

public interface JobTypeDAO {

    /**
     * Find all JobTypes
     * @return Τα βιβλία
     */
    List<JobType> findAll();

    /**
     * Save a JobType
     * @param entity JobType
     */
    void save(JobType entity);

    /**
     * Find the JobType with the given id
     * @param jobTypeID Ο κωδικός του βιβλίο
     * @return Το βιβλίο που βρέθηκε ή null
     */
    JobType find(int jobTypeID);
    
    /**
     * Find all Jobs for the JobType with the given id
     * @param jobTypeID JobType's id
     * @return JobType or null
     */
    JobType findAllJobs(int jobTypeID);


    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε ένα βιβλίο.
     * @return Ο κωδικός του βιβλίου
     */
    int nextId();
}
