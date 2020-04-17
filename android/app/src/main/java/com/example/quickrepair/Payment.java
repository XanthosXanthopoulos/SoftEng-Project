package com.example.quickrepair;

import java.util.Date;

public class Payment {
    private Date date;
    private PaymentType paymentType;

    public Payment(Date date, PaymentType paymentType) {
        this.date = date;
        this.paymentType = paymentType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
