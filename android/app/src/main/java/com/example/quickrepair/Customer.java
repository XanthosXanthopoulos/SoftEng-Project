package com.example.quickrepair;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Customer extends User
{
    /*
    * All Customer's Requests
     */
    private Set<RepairRequest> requests;

    /*
    * Costumer's constructor
     */
    public Customer(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        requests = new HashSet<RepairRequest>();
    }

    public void addRequest(RepairRequest request){
        if(request == null){ throw new IllegalArgumentException("Null RepairRequest");}
        requests.add(request);
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
}
