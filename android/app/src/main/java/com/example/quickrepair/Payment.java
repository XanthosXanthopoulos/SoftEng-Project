package com.example.quickrepair;

import java.time.LocalDateTime;

public class Payment
{
    private LocalDateTime date;
    private PaymentType paymentType;

    /**
     * Empty Constructor
     */
    public Payment()
    {
    }

    /**
     * Payment's Constructor
     *
     * @param date        payment's date
     * @param paymentType payment's type
     */
    public Payment(LocalDateTime date, PaymentType paymentType)
    {
        setDate(date);
        setPaymentType(paymentType);
    }

    /**
     * return payment's date
     */
    public LocalDateTime getDate()
    {
        return date;
    }

    /**
     * set payment's date
     *
     * @param date payment's date
     */
    public void setDate(LocalDateTime date)
    {
        if (date != null)
        {
            this.date = date;
        }
        else
        {
            throw new NullPointerException("null date");
        }
    }

    /**
     * return payment's type
     */
    public PaymentType getPaymentType()
    {
        return paymentType;
    }

    /**
     * set Payment's type
     *
     * @param paymentType payment's type
     */
    public void setPaymentType(PaymentType paymentType)
    {
        if (paymentType != null)
        {
            this.paymentType = paymentType;
        }
        else
        {
            throw new NullPointerException("null paymentType");
        }
    }
}
