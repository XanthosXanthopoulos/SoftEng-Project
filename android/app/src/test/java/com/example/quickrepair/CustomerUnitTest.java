package com.example.quickrepair;

import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class CustomerUnitTest{

    private Customer customerToTest;
    Job job;
    @Before
    public void setUpTests(){
        customerToTest = new Customer("nick" , "sm" , "6958375634" ,
                "sss222@asdm.com" , "123121231123" , "nicksm" ,
                "0j19283j1");
        job = new Job();
        Technician technician = new Technician("xrisa","dkn","1234567890","dkn@aueb.gr","1234","techo","password",new Specialty(),"1234");
        job.setTechnician(technician);
    }

    @Test (expected = IllegalArgumentException.class)
    public void chargeNegativeAmount(){
        customerToTest.chargeAccount(-12948);
    }

    @Test
    public void chargeNormalAmount(){
        customerToTest.chargeAccount(10000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addRequestNull(){
        customerToTest.addRequest(null);
    }
    @Test
    public void addRequestOk(){
        customerToTest.addRequest(new RepairRequest());
        Assert.assertNotNull(customerToTest.getRequests());
        Assert.assertEquals(1, customerToTest.getRequests().size());
    }

    @Test
    public void requestRepair(){
        GregorianCalendar date = new GregorianCalendar(2018,2,2);
        RepairRequest repairRequest = customerToTest.requestRepair(date, date, job , "blah" , new Address("ss" , "123"));
        Assert.assertNotNull(repairRequest);
    }
}
