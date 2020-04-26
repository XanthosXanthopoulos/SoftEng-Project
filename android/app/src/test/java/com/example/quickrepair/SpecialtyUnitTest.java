package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpecialtyUnitTest {
    Specialty specialty;
    @Before
    public void setUp(){
        specialty = new Specialty("HLEKTROLOGOS");
    }

    @Test (expected = NullPointerException.class)
    public void setNameNull(){
        specialty.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameSize(){
        specialty.setName("");
    }

    @Test (expected = NullPointerException.class)
    public void addJobTypeNull(){
        specialty.addJobType(null);
    }

    @Test
    public void addJobTypeok(){
        specialty.addJobType(new JobType("allagh lampas", specialty, MeasurementUnit.NONE));
        Assert.assertEquals(1, specialty.getJobTypes().size());
    }

    @Test
    public void equalsNull(){
        Assert.assertEquals(false, specialty.equals(null));
    }

    @Test
    public void equalsSameObject(){
        Assert.assertEquals(true, specialty.equals(specialty));
    }

    @Test
    public void equalsDifInstanceOf(){
        Assert.assertEquals(false, specialty.equals(new Integer(5)));
    }
    @Test
    public void equalsTrue(){
        Assert.assertEquals(true, specialty.equals(new Specialty("HLEKTROLOGOS")));
    }
    @Test
    public void equalsFalse(){
        Assert.assertEquals(false, specialty.equals(new Specialty("YDRAVLIKOS")));
    }

    @Test
    public void hashCodeTest(){
        Assert.assertEquals(specialty.getName().hashCode(), specialty.hashCode());
    }
}
