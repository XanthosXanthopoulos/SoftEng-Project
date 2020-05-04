package com.example.quickrepair;

import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.MeasurementUnit;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class JobUnitTest
{
    Job exampleJob;
    Technician exampleTechnician;
    Specialty exampleSpecialty;
    JobType exampleJobType;

    @Before
    public void setUpTests()
    {
        exampleSpecialty = new Specialty("124");
        exampleJobType = new JobType("plakakia", exampleSpecialty, MeasurementUnit.METER);
        exampleTechnician = new Technician("girgows", "papageorgiou", "6978564534",
                "giorgos@gmail.com", "12314", "1234", "123123", exampleSpecialty, "128307");
        exampleJob = new Job(exampleTechnician, exampleJobType, 1.5);
        exampleTechnician.setAFM("1587");
    }

    @Test
    public void testEquals()
    {
        Job other = new Job(exampleTechnician, exampleJobType, 1.5);
        Assert.assertEquals(exampleJob, other);
        Job wrongOther1 = new Job(exampleTechnician, exampleJobType, 1.5);
        Job wrongOther2 = new Job(exampleTechnician, exampleJobType, 1.6);
        Job wrongOther3 = null;
        Assert.assertNotEquals(wrongOther1, exampleTechnician);
        Assert.assertNotEquals(wrongOther2, exampleTechnician);
        Assert.assertNotEquals(wrongOther3, exampleTechnician);
    }

    @Test
    public void testGetters()
    {
        Assert.assertEquals(exampleJob.getJobType(), exampleJobType);
        Assert.assertTrue(exampleJob.getPrice() == 1.5);
        Assert.assertEquals(exampleJob.getTechnician(), exampleTechnician);
    }

    @Test
    public void testHashCode()
    {
        Job other = new Job(exampleTechnician, exampleJobType, 1.5);
        Assert.assertTrue(exampleJob.hashCode() == other.hashCode());
    }
}
