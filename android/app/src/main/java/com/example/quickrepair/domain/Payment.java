package com.example.quickrepair.domain;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class Payment
{
    private Integer uid;
    private Calendar date;
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
    public Payment(Calendar date, PaymentType paymentType)
    {
        setDate(date);
        setPaymentType(paymentType);
    }

    /**
     * return payment's date
     */
    public Calendar getDate()
    {
        return date;
    }

    /**
     * set payment's date
     *
     * @param date payment's date
     */
    private void setDate(Calendar date)
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
     * Gte the type of the payment.
     *
     * @return The type of payment
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
    private void setPaymentType(PaymentType paymentType)
    {
        if (paymentType == null)
        {
            throw new NullPointerException("null paymentType");
        }
        this.paymentType = paymentType;

    }

    /**
     * Get the payment UID.
     *
     * @return The payment UID.
     */
    public Integer getUid()
    {
        return uid;
    }

    /**
     * Set the payment UID.
     *
     * @param uid The payment UID.
     */
    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    @Override
    public boolean equals(@Nullable Object o)
    {
        if (o == null) return false;

        if (this == o) return true;

        if (!(o instanceof Payment)) return false;
        // typecast
        Payment payment = (Payment) o;
        // Compare the data members and return accordingly
        return payment.getDate().compareTo(this.getDate()) == 0 && payment.getPaymentType() == this.getPaymentType();
    }
}
