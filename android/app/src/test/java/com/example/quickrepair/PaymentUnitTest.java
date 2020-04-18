package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class PaymentUnitTest {

    @Test
    public void constructorTest() {
        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL, 18, 19, 30, 40);
        PaymentType paymentType = PaymentType.CARD;
        Payment payment = new Payment(date, paymentType);
        Assert.assertEquals(date, payment.getDate());
        Assert.assertEquals(paymentType, payment.getPaymentType());
    }

    //paymentType date
    @Test (expected = NullPointerException.class)
    public void nullDate(){
        Payment payment = new Payment();
        payment.setDate(null);
    }
    @Test
    public void okDate(){
        Payment payment = new Payment();
        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL, 18, 19, 30, 40);
        payment.setDate(date);
        Assert.assertEquals(date, payment.getDate());
    }

    //paymentType tests
    @Test (expected = NullPointerException.class)
    public void nullPaymentType(){
        Payment payment = new Payment();
        payment.setPaymentType(null);
    }
    @Test
    public void okPaymentType(){
        Payment payment = new Payment();
        PaymentType paymentType = PaymentType.CARD;
        payment.setPaymentType(paymentType);
        Assert.assertEquals(paymentType, payment.getPaymentType());
    }
}
