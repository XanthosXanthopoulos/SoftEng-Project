package com.example.quickrepair;

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


    int monday = 1;//MONDAY
    int april = GregorianCalendar.APRIL;//APRIL
    GregorianCalendar april6 = new GregorianCalendar(2020, april,6);
    GregorianCalendar start = new GregorianCalendar(2020, april, 6, 6, 0);
    GregorianCalendar end = new GregorianCalendar(2020, april, 6, 19, 0);

    GregorianCalendar april1010 = new GregorianCalendar(2020, Calendar.APRIL,10,10,10);
    GregorianCalendar newDateApril1010 = Utilities.getYearMonthDay(april1010);


    @Before
    public void setUpTests() {
        technicianToTest = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , new Specialty("test"));
        Address address = new Address("ath" , "15");
        exampleSpecialty = new Specialty("Electrician");
        exampleJobType = new JobType("Allagi plakakia" , exampleSpecialty , MeasurementUnit.METER);
        exampleAddress = address;
        exampleJob = new Job(technicianToTest , exampleJobType , 15 ,10);

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
        date1.add(date1.MINUTE,30);
        gap2.add(date1);
        gap2.add(end);
        gaps.add(gap2);

        Assert.assertEquals(2,technicianToTest.getAvailableHourRanges(april6).size());
        Assert.assertEquals(gap1,technicianToTest.getAvailableHourRanges(april6).get(0));
        Assert.assertEquals(gap2,technicianToTest.getAvailableHourRanges(april6).get(1));
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
        GregorianCalendar date2 = new GregorianCalendar(2020, Calendar.APRIL,6,1,10);
        repairRequest2.setConductionDate(date2);
        repairRequest2.setEstimatedDuration(20); //20 minutes to do the job, so he is going to have a gap at 1:20
        technicianToTest.notifyWithConfirmation(repairRequest2);


        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<ArrayList<GregorianCalendar>>();

        ArrayList<GregorianCalendar> gap1 = new ArrayList<GregorianCalendar>();
        gap1.add(start);
        gap1.add(date1);
        gaps.add(gap1);

        ArrayList<GregorianCalendar> gap2 = new ArrayList<GregorianCalendar>();
        date1.add(date1.MINUTE,30);
        gap2.add(date1);
        gap2.add(date2);
        gaps.add(gap2);

        ArrayList<GregorianCalendar> gap3 = new ArrayList<GregorianCalendar>();
        date2.add(date2.MINUTE,20);
        gap3.add(date2);
        gap3.add(end);
        gaps.add(gap3);


        Assert.assertEquals(gap1,technicianToTest.getAvailableHourRanges(april6).get(0));
        Assert.assertEquals(gap2,technicianToTest.getAvailableHourRanges(april6).get(1));
    }

}