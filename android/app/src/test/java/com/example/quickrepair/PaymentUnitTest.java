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
        Payment payment = new Payment(null , PaymentType.CARD);
    }

    @Test
    public void okDate(){
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
        Payment payment = new Payment(date , PaymentType.CARD);
        Assert.assertEquals(date, payment.getDate());
    }

    //paymentType tests
    @Test (expected = NullPointerException.class)
    public void nullPaymentType(){
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
        Payment payment = new Payment(date , null);
    }

    @Test
    public void okPaymentType(){
        PaymentType paymentType = PaymentType.CARD;
        GregorianCalendar date = new GregorianCalendar(2019,12,12);
        Payment payment = new Payment(date , paymentType);
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

        Payment payment = new Payment(new GregorianCalendar(2020,20,2) , PaymentType.CARD);
        Assert.assertNotEquals(payment,payment2);
    }
    @Test
    public void equalsOkNotEqualsPaymentType(){
        Payment payment2 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CARD);

        Payment payment = new Payment(new GregorianCalendar(2020,20,2) , PaymentType.CASH);
        Assert.assertNotEquals(payment , payment2);
    }
    @Test
    public void equalsOkEquals(){
        Payment payment2 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CARD);
        Payment payment1 = new Payment(new GregorianCalendar(2020,18,19),PaymentType.CARD);
        Assert.assertEquals(payment1 , payment2);
    }
}
