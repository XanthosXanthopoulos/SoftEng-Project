package com.example.quickrepair;

import java.time.LocalDateTime;

public class Customer extends User{
    public Customer(String name, String surname, String phoneNumber, String email, String bankAccount , String username , String password) {
        super(name,surname,phoneNumber,email,bankAccount,username , password);
    }

    public void chargeAccount(int totalCost){
        if (totalCost <= 0){
            throw new IllegalArgumentException("totalCost");
        }
        else{
            // The customers account is charged for the amount
        }
    }

    public RepairRequest requestRepair(LocalDateTime dateTime, Job job){
        RepairRequest repairRequest = new RepairRequest();

        repairRequest.setConductionDate(dateTime);
        repairRequest.setCustomer(this);

        job.getTechnician().addRepairRequest(repairRequest);

        return repairRequest;
    }
}
