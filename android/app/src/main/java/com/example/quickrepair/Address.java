package com.example.quickrepair;

public class Address
{
    private String streetName;
    private String number;

    public String getStreetName()
    {
        return streetName;
    }

    public void setStreetName(String streetName)
    {
        if (streetName == null) throw new NullPointerException();

        this.streetName = streetName;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        if(!number.matches("\\d+")) throw new IllegalArgumentException("Address number is invalid");

        this.number = number;
    }
}
