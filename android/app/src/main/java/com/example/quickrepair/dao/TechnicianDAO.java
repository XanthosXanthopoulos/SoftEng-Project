package com.example.quickrepair.dao;


import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Technician;

import java.util.List;
import java.util.Set;

public interface TechnicianDAO {
    /**
     * Delete a Technician
     * @param entity Technician
     */
    void delete(Technician entity);

    /**
     * Save a Technician
     * @param entity Technician
     */
    void save(Technician entity);

    /**
     * Find a Technician by id
     * @param technicianID Technician's id
     * @return Technician or null
     */
    Customer find(int technicianID);

    /**
     * Return all Technicians
     * @return Technicians
     */
    List<Technician> findAll();

    /**
     * Return all unconfirmed requests for a Technician
     * @returnunconfirmed RepairRequests for the Technician with the given id
     */
    Set<RepairRequest> findAllUnconfirmed(int technicianID);

    /**
     * Return all completed requests for a Technician
     * @return completed RepairRequests for the Technician with the given id
     */
    Set<RepairRequest> findAllCompletedRequests(int technicianID);

    /**
     * Return all confirmed requests for a Technician
     * @return confirmed RepairRequests for the Technician with the given id
     */
    Set<RepairRequest> findAllConfirmedRequests(int technicianID);

    /**
     * Return the next id for a Technician.
     * @return Technician's id
     */
    int nextId();

}
