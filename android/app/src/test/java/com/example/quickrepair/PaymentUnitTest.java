package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class PaymentUnitTest {

    Payment payment;
    @Before
    public void setUp(){
        payment = new Payment();
    }
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
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
        payment.setDate(date);
        Assert.assertEquals(date, payment.getDate());
    }

    //paymentType tests
    @Test (expected = NullPointerException.class)
    public void nullPaymentType(){
        payment.setPaymentType(null);
    }

    @Test
    public void okPaymentType(){
        PaymentType paymentType = PaymentType.CARD;
        payment.setPaymentType(paymentType);
        Assert.assertEquals(paymentType, payment.getPaymentType());
    }

    @Test
    public void equalsNull(){
        Assert.assertEquals(false,payment.equals(null));
    }
    @Test
    public void equalsThis(){
        Assert.assertEquals(true,payment.equals(payment));
    }
    @Test
    public void equalsDifInstanceOf(){
        Assert.assertEquals(false,payment.equals(new Evaluation()));
    }
    @Test
    public void equalsOkNotEqualsDates(){
        Payment payment2 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CARD);
        payment.setPaymentType(PaymentType.CARD);
        payment.setDate(new GregorianCalendar(2020,20,2));
        Assert.assertEquals(false,payment.equals(payment2));
    }
    @Test
    public void equalsOkNotEqualsPaymentType(){
        Payment payment2 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CASH);
        payment.setPaymentType(PaymentType.CARD);
        payment.setDate(new GregorianCalendar(2020,18,19));
        Assert.assertEquals(false,payment.equals(payment2));
    }
    @Test
    public void equalsOkEquals(){
        Payment payment2 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CARD);
        payment.setPaymentType(PaymentType.CARD);
        payment.setDate(new GregorianCalendar(2020,18,19));
        Assert.assertEquals(true, payment.equals(payment2));
    }
}
