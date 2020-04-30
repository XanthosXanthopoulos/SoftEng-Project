package com.example.quickrepair;

import com.example.quickrepair.domain.User;

import org.junit.Before;
import org.junit.Test;

public class UserUnitTest {

    User userToTest;
    @Before
    public void setUpTests() {
       userToTest = new User("nikos", "sm" , "6958475635",
               "asdih@ausdh.asdh" , "mybankaccount" , "nikos" , "123");
    }
    @Test
    public void setCorrectUserInfo(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectEmail(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdihausdh.asdh" , "mybankaccount" , "nikos");
    }
    @Test
    public void setCorrectEmailWithNumbers(){
        userToTest.setUserInfo("nikos", "sm" , "6958475635",
                "asdihausdh1999@asdd.asdh" , "mybankaccount" , "nikos");
    }
    @Test (expected = IllegalArgumentException.class)
    public void setIncorrectPhoneNumber(){
        userToTest.setUserInfo("nikos", "sm" , ";DROP TABLE USERS",
                "asdihausdh.asdh" , "mybankaccount" , "nikos");
    }
}
