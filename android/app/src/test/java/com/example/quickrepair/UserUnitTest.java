package com.example.quickrepair;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTest {

    User userToTest;
    @Before
    public void setUpTests() {
       userToTest = new User();
    }
    @Test
    public void setCorrectUserInfo(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectEmail(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdihausdh.asdh" , "mybankaccount");
    }
    @Test
    public void setCorrectEmailWithNumbers(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdihausdh1999@asdd.asdh" , "mybankaccount");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectPhoneNumber(){
        userToTest.setUserInfo("nikos", "sm" , ";DROP TABLE USERS",
                "asdihausdh.asdh" , "mybankaccount");
    }
}
