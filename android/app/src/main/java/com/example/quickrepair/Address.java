package com.example.quickrepair;

/**
 * Imuutable class that reprsents an address
 */
public class Address
{
    private String streetName;
    private String number;

    public Address(String streetName, String number){
        setStreetName(streetName);
        setNumber(number);
    }
    public String getStreetName()
    {
        return streetName;
    }

    private void setStreetName(String streetName)
    {
        if (streetName == null){ throw new NullPointerException();}

        this.streetName = streetName;
    }

    public String getNumber() { return number; }

    private void setNumber(String number)
    {
        //Only accept numbers if they are 1 or more digits long
        if (!number.matches("\\d+")) { throw new IllegalArgumentException("Address number is invalid");}
        this.number = number;
    }
}
