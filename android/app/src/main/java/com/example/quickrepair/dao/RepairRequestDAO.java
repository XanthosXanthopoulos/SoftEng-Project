package com.example.quickrepair.dao;


import com.example.quickrepair.domain.RepairRequest;

import java.util.List;

public interface RepairRequestDAO {
    /**
     * Delete an RepairRequest
     * @param entity RepairRequest
     */
    void delete(RepairRequest entity);

    /**
     * Find all RepairRequest
     * @return RepairRequest
     */
    List<RepairRequest> findAll();

    /**
     * Save a RepairRequest
     * @param entity RepairRequest
     */
    void save(RepairRequest entity);

    /**
     * Find the RepairRequest with the given id
     * @param RepairRequestID RepairRequest's id
     * @return RepairRequest or null
     */
    RepairRequest find(int RepairRequestID);

    /**
     * Return the next id for an RepairRequest.
     * @return Repair's id
     */
    int nextId();
}
