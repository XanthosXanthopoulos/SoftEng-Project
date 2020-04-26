package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobUnitTest {
    Job exampleJob;
    Technician exampleTechnician;
    Specialty exampleSpecialty;
    JobType exampleJobType;
    @Before
    public void setUpTests(){

        exampleSpecialty  = new Specialty("124");
        exampleJobType = new JobType("plakakia" , exampleSpecialty , MeasurementUnit.METER);
        exampleTechnician = new Technician("girgows" , "papageorgiou" , "6978564534" ,
                "giorgos@gmail.com" , "12314" , "1234" , "123123" , exampleSpecialty);
        exampleJob = new Job(exampleTechnician , exampleJobType , 1.5 , 500);
        exampleTechnician.setAFM("1587");
    }
    @Test
    public void testEquals(){
        Job other = new Job(exampleTechnician , exampleJobType , 1.5 , 500);
        Assert.assertEquals(exampleJob , other);
        Job wrongOther1 = new Job(exampleTechnician , exampleJobType , 1.5 , 501);
        Job wrongOther2 = new Job(exampleTechnician , exampleJobType , 1.6 , 500);
        Job wrongOther3 = null;
        Assert.assertNotEquals(wrongOther1 , exampleTechnician);
        Assert.assertNotEquals(wrongOther2 , exampleTechnician);
        Assert.assertNotEquals(wrongOther3 , exampleTechnician);
    }
    @Test
    public void testGetters(){
        Assert.assertEquals(exampleJob.getJobType() , exampleJobType);
        Assert.assertTrue(exampleJob.getPrice() == 1.5);
        Assert.assertEquals(exampleJob.getTechnician() , exampleTechnician);
    }

    @Test
    public void testHashCode(){
        Job other = new Job(exampleTechnician , exampleJobType , 1.5 , 500);
        Assert.assertTrue(exampleJob.hashCode() == other.hashCode());
        System.out.println(exampleJob.hashCode());
        Assert.assertTrue(exampleJob.hashCode() != 0 );
        Job jobWithNullJobType = new Job();
        Assert.assertTrue(jobWithNullJobType.hashCode() == 0);
    }
}
