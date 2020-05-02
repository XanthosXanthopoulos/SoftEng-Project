package com.example.quickrepair.domain;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Customer extends User
{
    /**
     * All Customer's Requests
     */
    private Set<RepairRequest> requests;

    public Customer()
    {
        requests = new HashSet<>();
    }

    /**
     * Costumer's constructor
     */
    public Customer(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        requests = new HashSet<>();
    }

    public void addRequest(RepairRequest request)
    {
        if (request == null)
        {
            throw new IllegalArgumentException("Null RepairRequest");
        }
        requests.add(request);
    }

    public Set<RepairRequest> getRequests()
    {
        return requests;
    }

    /**
     * Charge Customer's Account
     */
    public void chargeAccount(double totalCost)
    {
        if (totalCost <= 0)
        {
            throw new IllegalArgumentException("totalCost");
        }
        else
        {
            // The customers account is charged for the amount
        }
    }

    /**
     * Create a Repair Request for a specific job.
     *
     * @param dateNow The date of creation.
     * @param date The date of conduction.
     * @param job The requested job.
     * @param comments Additional comment of the customer for the technician.
     * @param address The address of the appointment.
     * @return The created repair request.
     */
    public RepairRequest requestRepair(GregorianCalendar dateNow, GregorianCalendar date, Job job, String comments, Address address)
    {
        RepairRequest repairRequest = new RepairRequest(this, job, dateNow, date, address, comments);

        requests.add(repairRequest);
        job.getTechnician().addRepairRequest(repairRequest);
        return repairRequest;
    }

    //TODO equals
}
