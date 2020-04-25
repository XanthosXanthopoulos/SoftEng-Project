package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.Calendar;

public class RepairRequestUnitTest {
    RepairRequest req;
    Calendar standardDate;
    @Before
    public void setUpTests(){
        standardDate = Calendar.getInstance();
        req = new RepairRequest();
        req.setPaymentType(PaymentType.CARD);
        Address exampleAddress = new Address("ath" , "15");
        req.setAddress(exampleAddress);
        req.setConductionDate(standardDate);;
    }
    @Test
    public void testExampleAddress(){

    }
}