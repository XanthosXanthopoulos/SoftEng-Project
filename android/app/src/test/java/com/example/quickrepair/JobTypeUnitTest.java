package com.example.quickrepair;

import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.MeasurementUnit;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobTypeUnitTest {
    Job exampleJob;
    Technician exampleTechnician;
    Specialty exampleSpecialty;
    JobType exampleJobType;

    @Before
    public void setUpTests(){

        exampleSpecialty  = new Specialty("124");
        exampleJobType = new JobType("plakakia" , exampleSpecialty , MeasurementUnit.METER);
        exampleTechnician = new Technician("girgows" , "papageorgiou" , "6978564534" ,
                "giorgos@gmail.com" , "12314" , "1234" , "123123" , exampleSpecialty , "129367");
        exampleJob = new Job(exampleTechnician , exampleJobType , 1.5 , 500);
        exampleTechnician.setAFM("1587");
    }
    @Test
    public void addJobsTest(){
        exampleJobType.addJob(exampleJob);
        Assert.assertTrue(exampleJobType.getJobs().contains(exampleJob));
        Technician otherTechnician = new Technician("kostas" , "papakostas" , "6972564534" ,
                "kostas@gmail.com" , "12314" , "kostas" , "123123" , exampleSpecialty, "123123");
        otherTechnician.setAFM("1151dsfdsd");
        Job otherTechniciansJobForthisJobType = new Job(otherTechnician , exampleJobType , 5 , 5);
        exampleJobType.addJob(otherTechniciansJobForthisJobType);
        Assert.assertTrue(exampleJobType.getJobs().contains(otherTechniciansJobForthisJobType));
    }
    @Test
    public void equalsTest(){
        JobType other = new JobType("asfaleia "  ,exampleSpecialty , MeasurementUnit.METER);
        Assert.assertFalse(other.equals(exampleJobType));
        Specialty newSpecialty = new Specialty("idravlikos");
        JobType otherSpecialty = new JobType("asfaleia "  ,newSpecialty , MeasurementUnit.METER);
        Assert.assertFalse(other.equals(otherSpecialty));

        JobType newObjectSameFields = new JobType("plakakia" , exampleSpecialty , MeasurementUnit.METER);
        Assert.assertEquals(newObjectSameFields , exampleJobType);

    }
    @Test
    public void testGetters(){
        exampleJobType.getSpecialty().equals(exampleSpecialty);
    }@Test
    public void testHashCode(){
        Assert.assertTrue(exampleJobType.hashCode() != 0);
        JobType other = new JobType();
        Assert.assertTrue(other.hashCode() == 0);
    }
}
