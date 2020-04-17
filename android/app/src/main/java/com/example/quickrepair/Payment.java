package com.example.quickrepair;

import java.time.LocalDateTime;

public class Payment {
    private LocalDateTime date;
    private PaymentType paymentType;

    /**
     * Empty Constructor
     */
    public Payment(){}

    /**
     * Payment's Constructor
     * @param date payment's title
     * @param paymentType payment's type
     */
    public Payment(LocalDateTime date, PaymentType paymentType) {
        this.date = date;
        this.paymentType = paymentType;
    }

    /**
     * return payment's date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * return payment's type
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

}
