package com.example.quickrepair;

import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.MeasurementUnit;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TechnicianUnitTest
{
    private Technician technicianToTest;
    private Address exampleAddress;
    private JobType exampleJobType;
    private Specialty exampleSpecialty;
    private Integer[][] exampleSchedule;

    @Before
    public void setUpTests()
    {
        exampleSpecialty = new Specialty("Electrician");
        technicianToTest = new Technician("nikos", "sm", "6958475635",
                "example@example.com", "mybankaccount", "nikos",
                "123", exampleSpecialty, "128947");
        Address address = new Address("ath", "15");

        exampleJobType = new JobType("Allagi plakakia", exampleSpecialty, MeasurementUnit.METER);
        exampleAddress = address;

        //Initializing schedule
        exampleSchedule = new Integer[7][2];
        for (int i = 0; i < 7; i++)
        {
            //example Technician works from 9
            exampleSchedule[i][0] = 9;
            //example Technician works until 5  (17 : 00)
            exampleSchedule[i][1] = 17;
        }
        technicianToTest.setSchedule(exampleSchedule);

    }

    @Test
    public void setCorrectSchedule()
    {
        technicianToTest.setSchedule(exampleSchedule);
        assertTrue(technicianToTest.isNormallyAvailable(0, 13));
        assertFalse(technicianToTest.isNormallyAvailable(0, 5));
        assertFalse(technicianToTest.isNormallyAvailable(0, 23));
        assertFalse(technicianToTest.isNormallyAvailable(0, 0));
        assertFalse(technicianToTest.isNormallyAvailable(6, 0));
    }

    @Test(expected = NullPointerException.class)
    public void setNullSchedule()
    {
        technicianToTest.setSchedule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMalformedSchedule()
    {
        Integer[][] malformedSchedule = new Integer[5][10];
        technicianToTest.setSchedule(malformedSchedule);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters1()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0, 1029839, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters2()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(-5, 9, 17);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters3()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0, 0, 24);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAvailableOnWithWrongParameters4()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0, 23, 23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNormallyAvailableWithWrongParameters1()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.isNormallyAvailable(7, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNormallyAvailableWithWrongParameters2()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.isNormallyAvailable(6, 24);
    }

    @Test(expected = NullPointerException.class)
    public void setScheduleWithNullEntries()
    {
        Integer[][] malformedSchedule = new Integer[7][2];
        for (int i = 0; i < 7; i++)
        {
            //example Technician works from 9
            exampleSchedule[i][0] = 9;
            //example Technician works until 5  (24 : 00)
            exampleSchedule[i][1] = null;
        }

        technicianToTest.setSchedule(malformedSchedule);
    }

    @Test
    public void changeScheduleForMonday()
    {
        technicianToTest.setSchedule(exampleSchedule);
        technicianToTest.setAvailableOnDay(0, 2, 5);
        for (int i = 5; i < 24; i++)
        {
            assertFalse(technicianToTest.isNormallyAvailable(0, i));
        }
        for (int i = 2; i < 5; i++)
        {
            assertTrue(technicianToTest.isNormallyAvailable(0, i));
        }
    }

    @Test
    public void setCorrectTechnicianInfo()
    {
        technicianToTest.setTechnicianInfo("nikos", "sm", "6958475635",
                "example@example.com", "mybankaccount", "username");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIncorrectEmail()
    {
        technicianToTest.setTechnicianInfo("nikos", "sm", "6958475635",
                "example.com", "mybankaccount", "username");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIncorrectPhoneNumber()
    {
        technicianToTest.setTechnicianInfo("nikos", "sm", "DROP TABLE USERS",
                "example@example.com", "mybankaccount", "username");
    }

    @Test
    public void setSpecialty()
    {
        technicianToTest.setSpecialty(exampleSpecialty);
    }

    @Test(expected = NullPointerException.class)
    public void addNullJob()
    {
        technicianToTest.addJob(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void setNullSpecialty()
    {
        technicianToTest.setSpecialty(null);
    }

    @Test
    public void testGetters()
    {
        technicianToTest.setSpecialty(exampleSpecialty);
        assertEquals(technicianToTest.getSpecialty(), exampleSpecialty);

        RepairRequest req = new RepairRequest();
        req.setAddress(exampleAddress);

        technicianToTest.setTechnicianInfo("nikos", "sm",
                "6958692431", "asd@gmail.com", "1111", "username");
        technicianToTest.setAFM("123142");
        assertEquals(technicianToTest.getEmail(), "asd@gmail.com");
        assertEquals(technicianToTest.getName(), "nikos");
        assertEquals(technicianToTest.getSurname(), "sm");
        assertEquals(technicianToTest.getPhoneNumber(), "6958692431");
        assertEquals(technicianToTest.getBankAccount(), "1111");
        assertTrue(technicianToTest.getAFM().equals("123142"));
    }

    @Test
    public void addJob()
    {
        technicianToTest.setSpecialty(exampleJobType.getSpecialty());
        technicianToTest.addJob(exampleJobType, 5);
        assertTrue(technicianToTest.getJobs().size() != 0);
    }

    @Test
    public void userNameAndPasswordTest()
    {
        Technician newTechnician = new Technician("nikos", "sm", "6958475635",
                "example@example.com", "mybankaccount", "nikos",
                "123", exampleSpecialty, "128947");
        assertEquals(newTechnician.getUsername(), "nikos");
        assertTrue(newTechnician.getPassword().equals("123"));
        assertFalse(newTechnician.getPassword().equals("1234"));
    }

    @Test(expected = NullPointerException.class)
    public void setNullUsername()
    {
        technicianToTest.setUsername(null);
    }

    @Test(expected = NullPointerException.class)
    public void setNullName()
    {
        technicianToTest.setTechnicianInfo(null, "", ""
                , "", "", "");
    }

    @Test(expected = NullPointerException.class)
    public void setNullSurname()
    {
        technicianToTest.setTechnicianInfo("", null, ""
                , "", "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsDayAvailableOutOfRange()
    {
        technicianToTest.isDayAvailable(-1);
    }

    @Test
    public void checkIsDayAvailableOk()
    {
        Assert.assertTrue(technicianToTest.isDayAvailable(1));
    }

    @Test
    public void testIsDayAvailableOkFalse()
    {
        technicianToTest.setAvailableOnDay(0, 0, 0);
        Assert.assertFalse(technicianToTest.isDayAvailable(0));
    }

    @Test
    public void equalsTest()
    {
        Technician otherTechnician = new Technician("nikos", "sm", "6958475635",
                "example@example.com", "mybankaccount", "nikos",
                "123", new Specialty("test"), "128947");

        assertEquals(technicianToTest, technicianToTest);
        assertNotEquals(technicianToTest, exampleAddress);
        assertNotEquals(technicianToTest, otherTechnician);

        otherTechnician = new Technician("nikos", "sm", "6958475635",
                "example@example.com", "mybankaccount", "nikos",
                "123", exampleSpecialty, "128947");
        otherTechnician.setSchedule(exampleSchedule);

        assertEquals(technicianToTest, otherTechnician);
    }

    @Test
    public void servesAreaTest()
    {
        technicianToTest.addArea("pagkrati");
        technicianToTest.addArea("athens");
        assertTrue(technicianToTest.servesArea("athens"));
        assertTrue(technicianToTest.servesArea("pagkrati"));
        assertFalse(technicianToTest.servesArea("assertFalse(technicianToTest.servesArea(\"\"));"));
        technicianToTest.removeArea("pagkrati");
        assertFalse(technicianToTest.servesArea("pagkrati"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void scheduleWithNot2entries()
    {
        Integer[][] badSchedule = {{3, 5}, {4, 5}, {3, 5, 6}, {4, 5}, {3, 5}, {4, 5}, {4, 5}
        };
        technicianToTest.setSchedule(badSchedule);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addJobToTechnicianWithNotTheAppropriateSpecialty()
    {
        //TODO remove all jobs when technician changes specialty
        technicianToTest.setSpecialty(exampleSpecialty);
        Specialty newSpecialty = new Specialty("ee");
        JobType newJobType = new JobType("ee", newSpecialty, MeasurementUnit.METER);
        technicianToTest.addJob(newJobType, 5);
    }

    @Test(expected = NullPointerException.class)
    public void removeNullJob()
    {
        technicianToTest.removeJob(null);
    }

    @Test
    public void setSpecialtyTest(){
        //When we change the specialty the previous offered jobs must be cleared
        Specialty newSpecialty = new Specialty("test");
        Job job = technicianToTest.addJob(exampleJobType , 15);
        Assert.assertTrue(technicianToTest.getJobs().size() != 0);
        technicianToTest.setSpecialty(newSpecialty);
        Assert.assertTrue(technicianToTest.getJobs().size() == 0);
    }

    @Test
    public void removeJobTest(){
        Specialty currSpecialty = technicianToTest.getSpecialty();
        Job job = technicianToTest.addJob(exampleJobType , 15);

        assertTrue(technicianToTest.getJobs().contains(job));
        assertTrue(exampleJobType.getJobs().contains(job));
        technicianToTest.removeJob(job);
        assertFalse(technicianToTest.getJobs().contains(job));
        assertFalse(exampleJobType.getJobs().contains(job));

    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddJob(){
        technicianToTest.addJob(exampleJobType,12);
        technicianToTest.addJob(exampleJobType,10);
    }
}