package com.example.quickrepair;

import org.junit.Before;
import org.junit.Test;

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
}
