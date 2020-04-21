package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;

public class RepairRequestUnitTest {
    RepairRequest req;
    LocalDateTime standardDate;
    @Before
    public void setUpTests(){
        standardDate = LocalDateTime.now();
        req = new RepairRequest();
        req.setPaymentType(PaymentType.CARD);
        Address exampleAddress = new Address();
        exampleAddress.setNumber("15");
        exampleAddress.setStreetName("ath");
        req.setAddress(exampleAddress);
        req.setConductionDate(standardDate);;
    }
    @Test
    public void testExampleAddress(){

    }
}