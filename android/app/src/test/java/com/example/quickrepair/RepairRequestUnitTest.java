package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

//TODO: MORE TESTS HERE
public class RepairRequestUnitTest {
    RepairRequest req;
    GregorianCalendar standardDate;
    @Before
    public void setUpTests(){
        standardDate =  new GregorianCalendar(2018,1,1,1,0);
        req = new RepairRequest();
        req.setPaymentType(PaymentType.CARD);
        Address exampleAddress = new Address("ath" , "15");
        req.setAddress(exampleAddress);
        req.setConductionDate(standardDate);;
    }
    @Test
    public void testExampleAddress(){

    }

    @Test (expected = IllegalStateException.class)
    public void compareToNotEqualsYear(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2019,2,2,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        repairRequest1.compareTo(repairRequest2);
    }

    @Test (expected = IllegalStateException.class)
    public void compareToNotEqualsMonths(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,2,2,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        repairRequest1.compareTo(repairRequest2);

    }
    @Test (expected = IllegalStateException.class)
    public void compareToNotEqualsDays(){
        RepairRequest repairRequest1 = new RepairRequest();
        RepairRequest repairRequest2 = new RepairRequest();
        GregorianCalendar c1 = new GregorianCalendar(2018,1,1,1,0);
        GregorianCalendar c2 = new GregorianCalendar(2018,1,2,1,0);
        repairRequest1.setConductionDate(c1);
        repairRequest2.setConductionDate(c2);
        repairRequest1.compareTo(repairRequest2);
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

}