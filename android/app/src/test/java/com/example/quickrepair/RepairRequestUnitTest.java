package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class RepairRequestUnitTest {
    RepairRequest req;
    GregorianCalendar standardDate;
    int price;
    @Before
    public void setUpTests(){
        standardDate =  new GregorianCalendar(2018,1,1,1,0);
        req = new RepairRequest();
        req.setPaymentType(PaymentType.CARD);
        Address exampleAddress = new Address("ath" , "15");
        req.setAddress(exampleAddress);
        req.setConductionDate(standardDate);
        Technician technicianToTest = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , new Specialty("HLEKTROLOGOS"), "128947");
        JobType jobType = new JobType("Allagh lampas",new Specialty("HLEKTROLOGOS"), MeasurementUnit.NONE);
        price = 12;
        Job job = new Job(technicianToTest, jobType, price,60);
        req.setJob(job);
        Customer customerToTest = new Customer("nick" , "sm" , "6958375634" ,
                "sss222@asdm.com" , "123121231123" , "nicksm" ,
                "0j19283j1");
        req.setCustomer(customerToTest);
    }
    @Test
    public void testExampleAddress(){
        Address addr = new Address("Patission","12");
        req.setAddress(addr);
        Assert.assertEquals(addr, req.getAddress());
    }

    @Test
    public void compareToEqualsHourAndMin(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        Assert.assertEquals(0, repairRequest1.compareTo(repairRequest2));
    }

    @Test
    public void compareToThisBeforeThatHour(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,2,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        Assert.assertEquals(-1, repairRequest1.compareTo(repairRequest2));
    }

    @Test
    public void compareToThisBeforeThatMin(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,1,1);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        Assert.assertEquals(-1, repairRequest1.compareTo(repairRequest2));
    }

    @Test
    public void compareToThisAfterThatHour(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,2,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        Assert.assertEquals(1, repairRequest1.compareTo(repairRequest2));
    }

    @Test
    public void compareToThisAfterThatMin(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,1);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        Assert.assertEquals(1, repairRequest1.compareTo(repairRequest2));
    }

    @Test
    public void sortedArrayOk(){
        ArrayList<RepairRequest> repairRequests = new ArrayList<RepairRequest>();
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,2,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);

        repairRequests.add(repairRequest1);
        repairRequests.add(repairRequest2);

        Collections.sort(repairRequests);

        Assert.assertEquals(repairRequest2 , repairRequests.get(0));
        Assert.assertEquals(repairRequest1 , repairRequests.get(1));
    }

    @Test
    public void sortedArrayOk2(){
        ArrayList<RepairRequest> repairRequests = new ArrayList<RepairRequest>();
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,1,2,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);

        repairRequests.add(repairRequest1);
        repairRequests.add(repairRequest2);

        Collections.sort(repairRequests);

        Assert.assertEquals(repairRequest1 , repairRequests.get(0));
        Assert.assertEquals(repairRequest2 , repairRequests.get(1));
    }

    @Test
    public void isCompletedFalse(){
        Assert.assertEquals(false, req.isCompleted());
    }

    @Test
    public void isCompletedTrue(){
        req.complete(2);
        Assert.assertEquals(true, req.isCompleted());
    }

    @Test
    public void isConfirmedFalse(){
        Assert.assertEquals(false, req.isConfirmed());
    }
    @Test
    public void isConfirmedTrue(){
        req.confirm(12);
        Assert.assertEquals(true, req.isConfirmed());
    }
    @Test (expected = IllegalStateException.class)
    public void confirmX2(){
        req.confirm(12);
        req.confirm(12);
    }
    @Test
    public void rejectTrue(){
        req.reject();
        Assert.assertEquals(false, req.isConfirmed());
    }

    @Test (expected = IllegalStateException.class)
    public void rejectX2(){
        req.reject();
        req.reject();
    }
}