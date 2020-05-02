package com.example.quickrepair;

import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Evaluation;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.MeasurementUnit;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.util.Utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
public class TechnicianUnitTest {
    Technician technicianToTest;
    Address exampleAddress;
    Job exampleJob;
    JobType exampleJobType;
    Specialty exampleSpecialty;
    Integer[][] exampleSchedule;
    RepairRequest repairRequest;
    RepairRequest repairRequest2;

    Customer exampleCustomer1;
    Customer exampleCustomer2;


    int monday = 1;//MONDAY
    int april = GregorianCalendar.APRIL;//APRIL
    GregorianCalendar april6 = new GregorianCalendar(2020, april,6);
    GregorianCalendar start = new GregorianCalendar(2020, april, 6, 6, 0);
    GregorianCalendar end = new GregorianCalendar(2020, april, 6, 19, 0);

    GregorianCalendar april1010 = new GregorianCalendar(2020, Calendar.APRIL,10,10,10);
    GregorianCalendar newDateApril1010 = Utilities.getYearMonthDay(april1010);


    @Before
    public void setUpTests() {
        exampleSpecialty = new Specialty("Electrician");
        technicianToTest = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , exampleSpecialty, "128947");
        Address address = new Address("ath" , "15");

        exampleJobType = new JobType("Allagi plakakia" , exampleSpecialty , MeasurementUnit.METER);
        exampleAddress = address;
        exampleJob = new Job(technicianToTest , exampleJobType , 15);

        //Initializing schedule
        exampleSchedule = new Integer[7][2];
        for (int i = 0; i < 7 ; i ++){
            //example Technician works from 9
            exampleSchedule[i][0] = 9;
            //example Technician works until 5  (17 : 00)
            exampleSchedule[i][1] = 17;
        }
        technicianToTest.setSchedule(exampleSchedule);
        repairRequest = new RepairRequest();
        repairRequest2 = new RepairRequest();
        exampleCustomer1 = new Customer("1" , "1" , "6972485968",
                "nikos.123123@gmail.com" , "124124" , "asdasd" , "password");
        exampleCustomer2 = new Customer("2" , "2" , "6972485968",
                "nikos.12344@gmail.com" , "114124" , "asdasda" , "password1");

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
        Integer[][] malformedSchedule = new Integer[5][10];
        technicianToTest.setSchedule(malformedSchedule);
    }
    @Test (expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters1(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0 , 1029839 , 1 );
    }
    @Test (expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters2(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(-5 , 9 , 17 );
    }
    @Test (expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters3(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0 , 0 , 24 );
    }
    @Test (expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters4(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0 , 23 , 23 );
    }
    @Test (expected = IllegalArgumentException.class)
    public void isNormallyAvailableWithWrongParameters1(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.isNormallyAvailable(7 , 5);
    }
    @Test (expected = IllegalArgumentException.class)
    public void isNormallyAvailableWithWrongParameters2(){
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.isNormallyAvailable(6 , 24);
    }
    @Test (expected = NullPointerException.class)
    public void setScheduleWithNullEntries(){
        Integer[][] malformedSchedule = new Integer[7][2];
        for (int i = 0 ; i < 7 ; i ++){
            //example Technician works from 9
            exampleSchedule[i][0] = 9;
            //example Technician works until 5  (24 : 00)
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
        repairRequest.setAddress(new Address("ath" , "15"));
        repairRequest.setConductionDate(new GregorianCalendar(2019,12,12));
        technicianToTest.addRepairRequest(repairRequest);
    }
    @Test
    public void setSpecialty(){
        technicianToTest.setSpecialty(exampleSpecialty);
    }

    @Test (expected = NullPointerException.class)
    public void addNullJob(){
        technicianToTest.addJob(null , 5 , 5);
    }
    @Test (expected = NullPointerException.class)
    public void setNullSpecialty(){
        technicianToTest.setSpecialty(null);
    }
    @Test (expected = NullPointerException.class)
    public void setNullRepairRequest(){
        technicianToTest.addRepairRequest(null);
    }
    /*
    @Test
    public void newtechnicianRepairsAreNotNull(){
        assertNotNull(technicianToTest.getRepairsList());
    }*/

    @Test
    public void testGetters(){
        technicianToTest.setSpecialty(exampleSpecialty);
        assertEquals(technicianToTest.getSpecialty() , exampleSpecialty);

        RepairRequest req = new RepairRequest();
        req.setAddress(exampleAddress);
        technicianToTest.addRepairRequest(req);

        assertEquals(
                technicianToTest.getRepairRequests().get(
                        technicianToTest.getRepairRequests().size() - 1 ),
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
        technicianToTest.setSpecialty(exampleJobType.getSpecialty());
        technicianToTest.addJob(exampleJobType , 5 , 6 );
        assertTrue(technicianToTest.getJobs().size() != 0);
    }

    @Test
    public void userNameAndPasswordTest(){
        Technician newTechnician = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , exampleSpecialty, "128947");
        assertEquals(newTechnician.getUsername() , "nikos");
        assertTrue(newTechnician.validatePassword("123"));
        assertFalse(newTechnician.validatePassword("1234"));
    }
    @Test (expected = NullPointerException.class)
    public void validateNullPassword(){
        technicianToTest.validatePassword(null);
    }
    @Test (expected = NullPointerException.class)
    public void setNullUsername(){
        technicianToTest.setUsername(null);
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

    @Test (expected = IllegalArgumentException.class)
    public void testIsDayAvailableOutOfRange(){
        technicianToTest.isDayAvailable(-1);
    }
    @Test
    public void checkIsDayAvailableOk(){
        Assert.assertTrue(technicianToTest.isDayAvailable(1));
    }
    @Test
    public void testIsDayAvailableOkFalse(){
        technicianToTest.setAvailableOnDay(0, 0 , 0 );
        Assert.assertFalse(technicianToTest.isDayAvailable(0));
    }
    @Test
    public void testNotifyWithConfirmationNewDate(){

        repairRequest.setConductionDate(april1010);
        technicianToTest.notifyWithConfirmation(repairRequest);

        ArrayList<RepairRequest> arrayWithReq = technicianToTest.getCalendarWithConfirmRequests().get(newDateApril1010);
        Assert.assertEquals(1,arrayWithReq.size());
        Assert.assertEquals(repairRequest, arrayWithReq.get(0));
    }

    @Test
    public void testNotifyWithConfirmation2NewDates(){

        GregorianCalendar date2 = new GregorianCalendar(2020, Calendar.APRIL,10,1,10);

        repairRequest.setConductionDate(april1010);
        repairRequest2.setConductionDate(date2);
        technicianToTest.notifyWithConfirmation(repairRequest);
        technicianToTest.notifyWithConfirmation(repairRequest2);

        ArrayList<RepairRequest> arrayWithReq = technicianToTest.getCalendarWithConfirmRequests().get(newDateApril1010);
        Assert.assertEquals(2,arrayWithReq.size());
        Assert.assertEquals(repairRequest, arrayWithReq.get(0));
        Assert.assertEquals(repairRequest2, arrayWithReq.get(1));
    }
    @Test
    public void testNotifyWithConfirmation2NewDatesDifferentDates(){

        GregorianCalendar date2 = new GregorianCalendar(2020, Calendar.APRIL,12,10,10);
        GregorianCalendar newDate2 = Utilities.getYearMonthDay(date2);

        repairRequest.setConductionDate(april1010);
        repairRequest2.setConductionDate(date2);
        technicianToTest.notifyWithConfirmation(repairRequest);
        technicianToTest.notifyWithConfirmation(repairRequest2);

        ArrayList<RepairRequest> arrayWithReq = technicianToTest.getCalendarWithConfirmRequests().get(newDateApril1010);
        Assert.assertEquals(1,arrayWithReq.size());
        Assert.assertEquals(repairRequest, arrayWithReq.get(0));

        ArrayList<RepairRequest> arrayWithReq2 = technicianToTest.getCalendarWithConfirmRequests().get(newDate2);
        Assert.assertEquals(1,arrayWithReq2.size());
        Assert.assertEquals(repairRequest2, arrayWithReq2.get(0));
    }
    @Test
    public void testGetAvailableHourRangesNotAvailableDay(){
        technicianToTest.setAvailableOnDay(monday, 0 , 0 );
        repairRequest.setConductionDate(april1010);
        technicianToTest.notifyWithConfirmation(repairRequest);
        Assert.assertEquals(null,technicianToTest.getAvailableHourRanges(april6));
    }

    @Test
    public void testGetAvailableHourRangesOneGap(){
        technicianToTest.setAvailableOnDay(monday, 6 , 19 );

        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<ArrayList<GregorianCalendar>>();
        ArrayList<GregorianCalendar> gap1 = new ArrayList<GregorianCalendar>();

        gap1.add(start);
        gap1.add(end);
        gaps.add(gap1);
        Assert.assertEquals(gaps,technicianToTest.getAvailableHourRanges(april6));
    }
    @Test
    public void testGetAvailableHourRanges2Gaps(){
        technicianToTest.setAvailableOnDay(monday, 6 , 19 );

        //repair request 1
        GregorianCalendar date1 = new GregorianCalendar(2020, Calendar.APRIL,6,8,30);
        repairRequest.setConductionDate(date1);
        repairRequest.setEstimatedDuration(30); //30 minutes to do the job, so he is going to have a gap at 9
        technicianToTest.notifyWithConfirmation(repairRequest);

        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<ArrayList<GregorianCalendar>>();

        ArrayList<GregorianCalendar> gap1 = new ArrayList<GregorianCalendar>();
        gap1.add(start);
        gap1.add(date1);
        gaps.add(gap1);

        ArrayList<GregorianCalendar> gap2 = new ArrayList<GregorianCalendar>();
        GregorianCalendar date1New = (GregorianCalendar) date1.clone();
        date1New.add(date1New.MINUTE,30);
        gap2.add(date1New);
        gap2.add(end);
        gaps.add(gap2);

        Assert.assertEquals(2 ,technicianToTest.getAvailableHourRanges(april6).size());
        Assert.assertEquals(gap1 ,technicianToTest.getAvailableHourRanges(april6).get(0));
        Assert.assertEquals(gap2 ,technicianToTest.getAvailableHourRanges(april6).get(1));
        Assert.assertEquals(gaps ,technicianToTest.getAvailableHourRanges(april6));
    }

    @Test
    public void testGetAvailableHourRanges3Gaps(){

        technicianToTest.setAvailableOnDay(monday, 6 , 19 );

        //repair request 1
        GregorianCalendar date1 = new GregorianCalendar(2020, Calendar.APRIL,6,8,30);
        repairRequest.setConductionDate(date1);
        repairRequest.setEstimatedDuration(30); //30 minutes to do the job, so he is going to have a gap at 9
        technicianToTest.notifyWithConfirmation(repairRequest);

        //repair request 2
        GregorianCalendar date2 = new GregorianCalendar(2020, Calendar.APRIL,6,13,10);
        repairRequest2.setConductionDate(date2);
        repairRequest2.setEstimatedDuration(20); //20 minutes to do the job, so he is going to have a gap at 1:20
        technicianToTest.notifyWithConfirmation(repairRequest2);


        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<ArrayList<GregorianCalendar>>();

        ArrayList<GregorianCalendar> gap1 = new ArrayList<GregorianCalendar>();
        gap1.add(start);
        gap1.add(date1);
        gaps.add(gap1);

        ArrayList<GregorianCalendar> gap2 = new ArrayList<GregorianCalendar>();
        GregorianCalendar date1New = (GregorianCalendar) date1.clone();
        date1New.add(date1New.MINUTE,30);
        gap2.add(date1New);
        gap2.add(date2);
        gaps.add(gap2);

        ArrayList<GregorianCalendar> gap3 = new ArrayList<GregorianCalendar>();
        GregorianCalendar date2New = (GregorianCalendar) date2.clone();
        date2New.add(date2New.MINUTE,20);
        gap3.add(date2New);
        gap3.add(end);
        gaps.add(gap3);

        Assert.assertEquals(3,technicianToTest.getAvailableHourRanges(april6).size());
        Assert.assertEquals(gap1,technicianToTest.getAvailableHourRanges(april6).get(0));
        Assert.assertEquals(gap2,technicianToTest.getAvailableHourRanges(april6).get(1));
        Assert.assertEquals(gap3,technicianToTest.getAvailableHourRanges(april6).get(2));
        Assert.assertEquals(gaps ,technicianToTest.getAvailableHourRanges(april6));
    }
    @Test
    public void equalsTest(){
        Technician otherTechnician = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , new Specialty("test"), "128947");
        Assert.assertEquals(technicianToTest , technicianToTest);
        Assert.assertNotEquals(technicianToTest , exampleAddress);
        Assert.assertEquals(otherTechnician , technicianToTest);
    }
    @Test
    public void servesAreaTest(){
        technicianToTest.addArea("pagkrati");
        technicianToTest.addArea("athens");
        assertTrue(technicianToTest.servesArea("athens"));
        assertTrue(technicianToTest.servesArea("pagkrati"));
        assertFalse(technicianToTest.servesArea("assertFalse(technicianToTest.servesArea(\"\"));"));
        technicianToTest.removeArea("pagkrati");
        assertFalse(technicianToTest.servesArea("pagkrati"));
    }
    @Test
    public void addRemoveJobTest(){
        technicianToTest.setSpecialty(exampleSpecialty);
        JobType jobType1 = new JobType("1" , exampleSpecialty  ,MeasurementUnit.METER);
        JobType jobType2 = new JobType("2" , exampleSpecialty  ,MeasurementUnit.METER);
        Job job1 = technicianToTest.addJob(jobType1 , 5 , 5);
        Job job2 = technicianToTest.addJob(jobType2, 10 , 10);
        //Checking that references have been added to jobtype and technician
        Assert.assertTrue(jobType1.getTechnicians().contains(technicianToTest));
        Assert.assertTrue(jobType2.getTechnicians().contains(technicianToTest));
        Assert.assertTrue(technicianToTest.offersJobForLessThanPrice(jobType1 , 10));
        Assert.assertTrue(technicianToTest.offersJobForLessThanPrice(jobType2 , 11));
        Assert.assertFalse(technicianToTest.offersJobForLessThanPrice(jobType2 , 10));
        //Checking reference integrity after removal of jobs
        technicianToTest.removeJob(job1);
        technicianToTest.removeJob(job2);
        Assert.assertFalse(jobType1.getTechnicians().contains(technicianToTest));
        Assert.assertFalse(jobType2.getTechnicians().contains(technicianToTest));
        Assert.assertFalse(technicianToTest.offersJobForLessThanPrice(jobType1 , 10000));
        Assert.assertFalse(technicianToTest.offersJobForLessThanPrice(jobType2 , 10000));
    }
    //TODO Test getEvaluations method

    @Test
    public void evaluationsTest(){
        RepairRequest repairRequest= exampleCustomer1.requestRepair((GregorianCalendar)Calendar.getInstance()
                , (GregorianCalendar)Calendar.getInstance() , exampleJob , "plshelp" , exampleAddress);
        //This job needs 160 minutes
        repairRequest.confirm(160);
        repairRequest.complete(150);
        repairRequest.getRepair().evaluate("Hallo" , "hallo" , 5);
        Evaluation eval = new Evaluation("Hallo" , "hallo" , 5);
        Evaluation a = new Evaluation("Hallo" , "hallo" , 5);
        assertTrue(technicianToTest.getEvaluations().contains(eval));
        RepairRequest repairRequest2= exampleCustomer1.requestRepair((GregorianCalendar)Calendar.getInstance()
                , (GregorianCalendar)Calendar.getInstance() , exampleJob , "oops you missed a spot" , exampleAddress);
        repairRequest.confirm(5);
        repairRequest.complete(1);
        repairRequest.getRepair().evaluate("5 stars he fixed my stairs" , "very " +
                "helpful would recommedn" , 5);
        Evaluation eval1 = new Evaluation("5 stars he fixed my stairs" , "very " +
                "helpful would recommedn" , 5);
        assertTrue(technicianToTest.getEvaluations().contains(eval1));
        Evaluation eval2 = new Evaluation("5 stars he fixed my stairs" , "very " +
                "helpful would recommesdn" , 5);
        assertFalse(technicianToTest.getEvaluations().contains(eval2));

    }


    @Test (expected = IllegalArgumentException.class)
    public void scheduleWithNot2entries(){
        Integer[][] badSchedule = {{3, 5} , {4 , 5} , {3, 5 , 6} , {4 , 5} , {3, 5} , {4 , 5} , {4 , 5}
        };
        technicianToTest.setSchedule(badSchedule);
    }
    @Test (expected = IllegalArgumentException.class)
    public void addJobToTechnicianWithNotTheAppropriateSpecialty(){
        //TODO remove all jobs when technician changes specialty
        technicianToTest.setSpecialty(exampleSpecialty);
        Specialty newSpecialty = new Specialty("ee");
        JobType newJobType = new JobType("ee" , newSpecialty , MeasurementUnit.METER);
        technicianToTest.addJob(newJobType ,  5 , 5);
    }
    @Test (expected = NullPointerException.class)
    public void removeNullJob(){
        technicianToTest.removeJob(null);
    }

}