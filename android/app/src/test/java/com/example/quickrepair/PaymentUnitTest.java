package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

public class PaymentUnitTest {

    @Test
    public void constructorTest() {
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
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
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
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
