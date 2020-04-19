package com.example.quickrepair;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;
public class TechnicianUnitTest {
    Technician technicianToTest;
    @Before
    public void setUpTests() {
        technicianToTest = new Technician();
    }
    @Test
    public void setCorrectTechnicianInfo(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectEmail(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , "6958475635",
                "asdihausdh.asdh" , "mybankaccount");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectPhoneNumber(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , ";DROP TABLE USERS",
                "asdihausdh.asdh" , "mybankaccount");
    }
    @Test
    public void setRepairRequest(){
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setAddress(new Address());
        repairRequest.setDate(LocalDateTime.now());
        repairRequest.setPaymentType(PaymentType.CASH);
        technicianToTest.setRepairRequest(repairRequest);
    }
    @Test
    public void setSpecialty(){
        technicianToTest.setSpecialty(Specialty.ELECTRICIAN);
    }

    @Test (expected = NullPointerException.class)
    public void addNullJob(){
        technicianToTest.addJob(null);
    }
    @Test (expected = NullPointerException.class)
    public void setNullSpecialty(){
        technicianToTest.setSpecialty(null);
    }
    @Test (expected = NullPointerException.class)
    public void setNullRepairRequest(){
        technicianToTest.setRepairRequest(null);
    }
    @Test
    public void newtechnicianRepairsAreNotNull(){
        assertNotNull(technicianToTest.getRepairs());
    }
}
