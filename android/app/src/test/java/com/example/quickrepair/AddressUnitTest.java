package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
public class AddressUnitTest {
    @Test
    public void testExampleAddress(){
        Address addr = new Address();
        addr.setNumber("15");
        addr.setStreetName("Ath");
        Assert.assertEquals(addr.getNumber()  , "15");
        Assert.assertEquals(addr.getStreetName()  , "Ath");
    }
}
