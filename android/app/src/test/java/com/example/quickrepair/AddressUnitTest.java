package com.example.quickrepair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
public class AddressUnitTest {
    @Test
    public void testExampleAddress(){
        Address addr = new Address("Ath" , "123");
        Assert.assertEquals(addr.getNumber()  , "123");
        Assert.assertEquals(addr.getStreetName()  , "Ath");
    }
    @Test (expected = IllegalArgumentException.class)
    public void newAddressWithIncorrectNumber(){
        Address addr = new Address("14" , "aaaa");
    }
}
