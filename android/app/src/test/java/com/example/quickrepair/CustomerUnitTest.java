package com.example.quickrepair;

import org.junit.Before;
import org.junit.Test;

public class CustomerUnitTest {

    Customer customerToTest;

    @Before
    public void setUpTests() {
        customerToTest = new Customer();
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
