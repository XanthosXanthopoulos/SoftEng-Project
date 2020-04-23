package com.example.quickrepair;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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
        if (paymentType == null){throw new NullPointerException("null paymentType");}
        this.paymentType = paymentType;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean equals(@Nullable Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Payment)) { return false; }
        // typecast
        Payment payment = (Payment) o;
        // Compare the data members and return accordingly
        return payment.getDate().compareTo(this.getDate())==0 && payment.getPaymentType()==this.getPaymentType();
    }
}
