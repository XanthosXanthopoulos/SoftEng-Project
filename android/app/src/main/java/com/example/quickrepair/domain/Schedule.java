package com.example.quickrepair.domain;

import java.util.ArrayList;

public class Schedule
{
    ArrayList<ScheduleEntry> entries;

    public Schedule()
    {
        entries = new ArrayList<>();

        for (int i = 0; i < 7; ++i)
        {
            entries.add(new ScheduleEntry());
        }
    }

    public void setSchedule(int day, int startingHour, int endingHour)
    {
        if (day <= 0 || day >= 7) throw new IllegalArgumentException("Day should be between zero and six inclusive");

        if (startingHour < 0 || startingHour > 23)  throw new IllegalArgumentException("Day should be between zero and twenty three inclusive");

        if (endingHour < 0 || endingHour > 23)  throw new IllegalArgumentException("Day should be between zero and twenty three inclusive");

        entries.set(day, new ScheduleEntry(startingHour, endingHour));
    }

    public ScheduleEntry getSchedule(int day)
    {
        if (day <= 0 || day >= 7) throw new IllegalArgumentException("Day should be between zero and six inclusive");

        return new ScheduleEntry(entries.get(day));
    }

    public static class ScheduleEntry
    {
        private int startingHour;
        private int endingHour;

        public ScheduleEntry()
        {
            startingHour = 0;
            endingHour  = 0;
        }

        public ScheduleEntry(int startingHour, int endingHour)
        {
            this.startingHour = startingHour;
            this.endingHour = endingHour;
        }

        public ScheduleEntry(ScheduleEntry entry)
        {
            this.startingHour = entry.startingHour;
            this.endingHour = entry.endingHour;
        }

        public int getStartingHour()
        {
            return startingHour;
        }

        public int getEndingHour()
        {
            return endingHour;
        }
    }
}
