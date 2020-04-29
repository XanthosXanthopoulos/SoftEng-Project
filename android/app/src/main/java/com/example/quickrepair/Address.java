package com.example.quickrepair;

/**
 * Immutable class that represents an address
 */
public final class Address
{
    private String streetName;
    private String number;

    public Address(String streetName, String number)
    {
        setStreetName(streetName);
        setNumber(number);
    }

    public String getStreetName()
    {
        return streetName;
    }

    private void setStreetName(String streetName)
    {
        if (streetName == null)
        {
            throw new NullPointerException("streetName can't be null");
        }

        this.streetName = streetName;
    }

    public String getNumber()
    {
        return number;
    }

    private void setNumber(String number)
    {
        if (number == null) throw new NullPointerException();

        //Only accept numbers if they are 1 or more digits long
        if (!number.matches("\\d+"))
        {
            throw new IllegalArgumentException("Address number is invalid");
        }

        this.number = number;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetName.equals(address.getStreetName()) &&
                number.equals(address.getNumber());
    }

    @Override
    public int hashCode()
    {
        return (streetName + number).hashCode();
    }
}
