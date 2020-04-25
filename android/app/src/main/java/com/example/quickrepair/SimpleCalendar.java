package com.example.quickrepair;

import java.util.Calendar;

public class SimpleCalendar
{
    private static final long MILLIS_PER_DAY = 86400000;
    private Calendar date;

    public SimpleCalendar(int year, int month, int day)
    {
        date = Calendar.getInstance();
        date.set(year, month, day, 0, 0, 0);
        date.set(Calendar.MILLISECOND, 0);
    }

    public SimpleCalendar(int year, int month, int day, int hour, int minute)
    {
        date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);
        date.set(Calendar.MILLISECOND, 0);
    }

    public SimpleCalendar(Calendar date, boolean trimHours)
    {
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(date.getTimeInMillis());

        if (trimHours)
        {
            date.set(Calendar.HOUR_OF_DAY, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
        }
    }

    /**
     * Επιστρέφει το έτος της ημερομηνίας.
     *
     * @return Το έτος
     */
    public int getYear()
    {
        return date.get(Calendar.YEAR);
    }

    /**
     * Επιστρέφει το μήνα της ημερομηνίας (1-12).
     *
     * @return Ο μήνας
     */
    public int getMonth()
    {
        return date.get(Calendar.MONTH);
    }

    /**
     * Επιστρέφει την ημέρα σε του μήνα.
     *
     * @return Η ημέρα του μήνα
     */
    public int getDayOfMonth()
    {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Επιστρέφει την ημέρα της εβδομάδας της ημερομηνίας.
     *
     * @return Η ημέρα της εβδομάδας
     */
    public int getDayOfWeek()
    {
        return date.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Επιστρέφει την ώρα της ημερομηνίας.
     *
     * @return Η ημέρα της εβδομάδας
     */
    public int getHourOfDay()
    {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Επιστρέφει τα λεπτά της ημερομηνίας.
     *
     * @return Η ημέρα της εβδομάδας
     */
    public int getMinute()
    {
        return date.get(Calendar.MINUTE);
    }

    public boolean after(SimpleCalendar other)
    {
        if (equals(other)) return false;

        return date.after(other.date);
    }

    /**
     * Επιστρέφει {@code true} αν η ημερομηνία είναι.
     * προγενέστερη μίας άλλης ημερομηνίας
     * @param other Η άλλη ημερομηνία
     * @return {@code true} αν η ημερομηνία είναι
     * προγενέστερη της άλλης
     */
    public boolean before(SimpleCalendar other)
    {
        if (equals(other)) return false;

        return date.before(other.date);
    }

    /**
     * Επιστρέφει μία ημερομηνία προσθέτοντας κάποιο
     * αριθμό ημερών.
     * @param hours Ο αριθμός των ημερών που προστίθενται
     * @return Η νέα ημερομηνία
     */
    public SimpleCalendar addHours(int hours)
    {
        Calendar newDate = Calendar.getInstance();
        newDate.setTimeInMillis(date.getTimeInMillis());
        newDate.add(Calendar.HOUR_OF_DAY, hours);
        return new SimpleCalendar(newDate, false);
    }

    /**
     * Επιστρέφει μία ημερομηνία προσθέτοντας κάποιο
     * αριθμό ημερών.
     * @param minutes Ο αριθμός των ημερών που προστίθενται
     * @return Η νέα ημερομηνία
     */
    public SimpleCalendar addMinutes(int minutes)
    {
        Calendar newDate = Calendar.getInstance();
        newDate.setTimeInMillis(date.getTimeInMillis());
        newDate.add(Calendar.MINUTE, minutes);
        return new SimpleCalendar(newDate, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;

        if (this == other) return true;

        if (!(other instanceof SimpleCalendar)) return false;

        SimpleCalendar theDate = (SimpleCalendar) other;

        if (date == null) return theDate.date == null;

        if (getYear() != theDate.getYear()) return false;

        if (getMonth() != theDate.getMonth()) return false;

        if (getDayOfMonth() != theDate.getDayOfMonth()) return false;

        if (getHourOfDay() != theDate.getHourOfDay()) return false;

        if (getMinute() != theDate.getMinute()) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return date == null ? 0 : date.hashCode();
    }
}
