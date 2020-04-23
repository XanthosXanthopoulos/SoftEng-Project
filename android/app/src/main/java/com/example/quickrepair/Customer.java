package com.example.quickrepair;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Customer extends User
{
    public Customer(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
    }

    public void chargeAccount(int totalCost)
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

    //TODO This doesnt belog here
    public RepairRequest requestRepair(Calendar date, Job job)
    {
        RepairRequest repairRequest = new RepairRequest();

        repairRequest.setConductionDate(date);
        repairRequest.setCustomer(this);

        job.getTechnician().addRepairRequest(repairRequest);

        return repairRequest;
    }
}
