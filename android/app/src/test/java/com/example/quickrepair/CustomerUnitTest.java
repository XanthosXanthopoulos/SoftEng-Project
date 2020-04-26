package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class CustomerUnitTest{

    private Customer customerToTest;

    @Before
    public void setUpTests(){
        customerToTest = new Customer("nick" , "sm" , "6958375634" ,
                "sss222@asdm.com" , "123121231123" , "nicksm" ,
                "0j19283j1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void chargeNegativeAmount(){
        customerToTest.chargeAccount(-12948);
    }

    @Test
    public void chargeNormalAmount(){
        customerToTest.chargeAccount(10000);
    }
    /*
    @Test
    public void requestRepair(){
        GregorianCalendar date = new GregorianCalendar(2018,2,2);
        Job job= new Job();
        RepairRequest repairRequest = new RepairRequest();

        customerToTest.requestRepair(date, job);

    }*/
}
