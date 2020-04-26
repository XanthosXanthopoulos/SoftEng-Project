package com.example.quickrepair;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Customer extends User
{
    /*
    * All Customer's Requests
     */
    Set<RepairRequest> requests;

    /*
    * Costumer's constructor
     */
    public Customer(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        requests = new HashSet<RepairRequest>();
    }

    public void setRequests(Set<RepairRequest> requests) {
        if(requests == null){ throw new NullPointerException();}
        this.requests = requests;
    }

    public Set<RepairRequest> getRequests() {
        return requests;
    }

    /*
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

    /*
    *
     */
    public RepairRequest requestRepair(GregorianCalendar date, Job job)
    {
        RepairRequest repairRequest = new RepairRequest();

        repairRequest.setConductionDate(date);
        repairRequest.setCustomer(this);

        requests.add(repairRequest);
        job.getTechnician().addRepairRequest(repairRequest);

        return repairRequest;
    }
}
