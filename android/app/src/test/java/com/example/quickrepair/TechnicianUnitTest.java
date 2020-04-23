package com.example.quickrepair;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
public class TechnicianUnitTest {
    Technician technicianToTest;
    Address exampleAddress;
    Job exampleJob;
    JobType exampleJobType;
    Specialty exampleSpecialty;
    Calendar[][] exampleSchedule;
    @Before
    public void setUpTests() {
        technicianToTest = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , new Specialty("test"));
        Address address = new Address();
        address.setStreetName("ath");
        address.setNumber("15");
        exampleJobType = new JobType("Allagi plakakia");
        exampleAddress = address;
        exampleJob = new Job(technicianToTest , exampleJobType , 15 ,10);
        exampleSpecialty = new Specialty("Electrician");
        //Initializing schedule
        exampleSchedule = new Calendar[7][2];
        for (int i = 0 ; i < 7 ; i ++){
            //example Technician works from 9
            exampleSchedule[i][0] = Calendar.getInstance();
            exampleSchedule[i][0].set(Calendar.HOUR_OF_DAY , 9);
            //example Technician works until 5  (17 : 00)
            exampleSchedule[i][1] = Calendar.getInstance();
            exampleSchedule[i][1].set(Calendar.HOUR_OF_DAY , 17);
            //System.out.println(exampleSchedule[i][0]);
            //System.out.println(exampleSchedule[i][1]);
            //System.out.println();
        }



    }
    @Test
    public void setCorrectSchedule(){
        technicianToTest.setSchedule(exampleSchedule);
        assertTrue(technicianToTest.isNormallyAvailable(0 , 13));
        assertFalse(technicianToTest.isNormallyAvailable(0 , 5));
        assertFalse(technicianToTest.isNormallyAvailable(0 , 23));
        assertFalse(technicianToTest.isNormallyAvailable(0 , 0));
        assertFalse(technicianToTest.isNormallyAvailable(6 , 0));
    }
    @Test (expected = NullPointerException.class)
    public void setNullSchedule(){
        technicianToTest.setSchedule(null);
    }
    @Test (expected = IllegalArgumentException.class)
    public void setMalformedSchedule(){
        Calendar[][] malformedSchedule = new Calendar[5][10];
        technicianToTest.setSchedule(malformedSchedule);
    }
    @Test (expected = NullPointerException.class)
    public void setScheduleWithNullEntries(){
        Calendar[][] malformedSchedule = new Calendar[7][2];
        for (int i = 0 ; i < 7 ; i ++){
            //example Technician works from 9
            exampleSchedule[i][0] = Calendar.getInstance();
            exampleSchedule[i][0].set(Calendar.HOUR_OF_DAY , 9);
            //example Technician works until 5  (17 : 00)
            exampleSchedule[i][1] = null;
        }

        technicianToTest.setSchedule(malformedSchedule);
    }
    @Test
    public void changeScheduleForMonday(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0 , 2 , 5);
        for(int i = 5 ; i < 24 ; i ++){
            assertFalse(technicianToTest.isNormallyAvailable(0,i));
        }
        for(int i = 2 ; i < 5 ; i ++){
            assertTrue(technicianToTest.isNormallyAvailable(0,i));
        }
    }
    @Test
    public void setCorrectTechnicianInfo(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "username");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectEmail(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , "6958475635",
                "asdihausdh.asdh" , "mybankaccount" , "username");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectPhoneNumber(){
        technicianToTest.setTechnicianInfo("nikos", "sm" , ";DROP TABLE USERS",
                "asdihausdh.asdh" , "mybankaccount" , "username");
    }
    @Test
    public void setRepairRequest(){
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setAddress(new Address());
        repairRequest.setConductionDate(LocalDateTime.now());
        repairRequest.setPaymentType(PaymentType.CASH);
        technicianToTest.addRepairRequest(repairRequest);
    }
    @Test
    public void setSpecialty(){
        technicianToTest.setSpecialty(exampleSpecialty);
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
        technicianToTest.addRepairRequest(null);
    }
    @Test
    public void newtechnicianRepairsAreNotNull(){
        assertNotNull(technicianToTest.getRepairsList());
    }

    @Test
    public void testGetters(){
        technicianToTest.setSpecialty(exampleSpecialty);
        assertEquals(technicianToTest.getSpecialty() , exampleSpecialty);

        RepairRequest req = new RepairRequest();
        req.setPaymentType(PaymentType.CASH);
        req.setAddress(exampleAddress);
        technicianToTest.addRepairRequest(req);

        assertEquals(
                technicianToTest.getPendingRequests().get(
                        technicianToTest.getPendingRequests().size() - 1 ),
                req
        );

        technicianToTest.setTechnicianInfo("nikos" , "sm" ,
                "6958692431" , "asd@gmail.com" , "1111" , "username");
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
                , "" , "" , "");
    }

    @Test (expected = NullPointerException.class)
    public void setNullSurname(){
        technicianToTest.setTechnicianInfo("" , null , ""
                , "" , "", "");
    }
}
