package com.example.quickrepair;

public class Customer extends User {

    public void chargeAccount(int totalCost){
        if (totalCost <= 0){
            throw new IllegalArgumentException("totalCost");
        }
        else{
            // The customers account is charged for the amount
        }
    }

}
