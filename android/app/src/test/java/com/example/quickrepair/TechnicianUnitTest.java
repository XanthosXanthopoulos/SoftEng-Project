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
    Address exampleAddress;
    Job exampleJob;
    @Before
    public void setUpTests() {
        technicianToTest = new Technician();
        Address address = new Address();
        address.setStreetName("ath");
        address.setNumber("15");
        exampleAddress = address;
        exampleJob = new Job(technicianToTest , new JobType("plakakia") , 15);

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
        assertNotNull(technicianToTest.getRepairsList());
    }

    @Test
    public void testGetters(){
        technicianToTest.setSpecialty(Specialty.ELECTRICIAN);
        assertEquals(technicianToTest.getSpecialty() , Specialty.ELECTRICIAN);

        RepairRequest req = new RepairRequest();
        req.setPaymentType(PaymentType.CASH);
        req.setAddress(exampleAddress);
        technicianToTest.setRepairRequest(req);

        assertEquals(
                technicianToTest.getPendingRequests().get(
                        technicianToTest.getPendingRequests().size() - 1 ),
                req
        );

        technicianToTest.setTechnicianInfo("nikos" , "sm" ,
                "6958692431" , "asd@gmail.com" , "1111");
        assertEquals(technicianToTest.getEmail() , "asd@gmail.com");
        assertEquals(technicianToTest.getName() , "nikos");
        assertEquals(technicianToTest.getSurname() , "sm");
        assertEquals(technicianToTest.getPhoneNumber() , "6958692431");
        assertEquals(technicianToTest.getBankAccount() , "1111");
    }
    @Test
    public void addJob(){
        technicianToTest.addJob(exampleJob);
        assertTrue(technicianToTest.getJobs().size() != 0);
    }

    @Test
    public void userNameAndPasswordTest(){
        technicianToTest.setUsername("the best electrician of the world");
        assertEquals(technicianToTest.getUsername() , "the best electrician of the world");

        technicianToTest.setPassword("123141");
        assertEquals(technicianToTest.getPassword() , "123141");
    }

    @Test (expected = NullPointerException.class)
    public void setNullUsername(){
        technicianToTest.setUsername(null);
    }

    @Test (expected = NullPointerException.class)
    public void setNullPassword(){
        technicianToTest.setPassword(null);
    }

    @Test (expected = NullPointerException.class)
    public void setNullName(){
        technicianToTest.setTechnicianInfo(null , "" , ""
                , "" , "");
    }

    @Test (expected = NullPointerException.class)
    public void setNullSurname(){
        technicianToTest.setTechnicianInfo("" , null , ""
                , "" , "");
    }
}
