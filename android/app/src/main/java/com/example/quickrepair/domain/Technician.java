package com.example.quickrepair.domain;

import com.example.quickrepair.util.Utilities;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Technician extends User
{
    private String AFM;
    private Set<Job> jobs = new HashSet<>();
    private Specialty specialty;
    private HashSet<String> areas = new HashSet<>();

    //This structure will set up by the technician
    //it is necessary for a costumer to know when the technician is on duty
    //for each day of the week -> (startHour, endHour)
    private Integer[][] schedule = new Integer[7][2];

    // Constructors
    public Technician(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password, Specialty specialty, String AFM)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        setSpecialty(specialty);
        setAFM(AFM);
    }

    //SETTERS

    /**
     * Set's this technician's specialty
     *
     * @param specialty Technician's specialty
     */
    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        for (Job job : jobs)
        {
            job.getJobType().removeJob(job);
        }
        jobs.clear();

        this.specialty = specialty;
    }

    /**
     * Sets the technician personal info performing the necessary checks
     */
    public void setTechnicianInfo(String name, String surname, String phoneNumber, String email, String bankAccount, String username)
    {
        setUserInfo(name, surname, phoneNumber, email, bankAccount, username);
    }

    /**
     * Sets the technicians schedule performing the necessary checks
     *
     * @param schedule
     */
    //TODO input hours of day can be set until 24 if the technician can literally work all day
    public void setSchedule(Integer[][] schedule)
    {
        if (schedule == null)
        {
            throw new NullPointerException("null schedule");
        }

        if (schedule.length != 7)
        {
            throw new IllegalArgumentException("The schedule must have 7 entries");
        }

        for (int i = 0; i < 7; i++)
        {
            if (schedule[i] == null || schedule[i].length != 2)
            {
                throw new IllegalArgumentException("Every entry must have 2 calendars");
            }

            if (schedule[i][0] == null || schedule[i][1] == null)
            {
                throw new NullPointerException("null schedule entries on " + i);
            }

        }

        this.schedule = schedule;
    }

    public void setAFM(String AFM)
    {
        if (AFM == null) throw new NullPointerException("null AFM");
        this.AFM = AFM;
    }

    public Set<Job> getJobs()
    {
        return jobs;
    }

    public Specialty getSpecialty()
    {
        return specialty;
    }

    public HashSet<String> getAreas()
    {
        return areas;
    }

    public Integer[][] getSchedule()
    {
        return schedule;
    }

    public String getAFM()
    {
        return AFM;
    }

    /**
     * Adds a job to the technician's list of jobs
     * Also updates the association references
     */
    public Job addJob(JobType jobType, double price)
    {
        if (jobType == null) throw new NullPointerException();
        if (!jobType.getSpecialty().equals(getSpecialty())) throw new IllegalArgumentException("A technician can only offer jobs from his specialty");
        if (price <= 0) throw new IllegalArgumentException();

        Job job = new Job(this, jobType, price);
        jobs.add(job);
        jobType.addJob(job);

        return job;
    }

    /**
     * Removes a job from the technicians list of job
     * Also updates the list of jobs that offer the job's jobtype
     */
    public void removeJob(Job job)
    {
        if (job == null) throw new NullPointerException();
        if (!job.getTechnician().equals(this)) throw new IllegalArgumentException();

        getJobs().remove(job);
        job.getJobType().removeJob(job);
    }

    /**
     * Adds an area to the list of this technicians areas
     *
     * @param area the area to be added
     */
    public void addArea(String area)
    {
        if (area == null) throw new NullPointerException();

        areas.add(area);
    }

    /**
     * Removes an area from the list of this technicians areas
     *
     * @param area the area to be removed
     */
    public void removeArea(String area)
    {
        if (area == null) throw new NullPointerException();

        areas.remove(area);
    }

    /**
     * Checks if the technician serves the given area
     *
     * @param area the area we are testing
     * @return true if the technician serves the specified area
     */
    public boolean servesArea(String area)
    {
        if (area == null) throw new NullPointerException();

        return getAreas().contains(area);
    }

    /**
     * Marks this technician as available the given day of the week from @param hourStart until
     *
     * @param hourEnd   Ideal
     *                  Example usage :  setAvailableOnDay(Calendar.MONDAY , 15 , 20)  to mark a technician available
     *                  on monday from 15:00 to 20:00
     *                  <p>
     *                  Current
     *                  Example usage :  setAvailableOnDay(Calendar.0 , 15 , 20)  to mark a technician available
     *                  * on monday from 15:00 to 20:00
     * @param day       0 for Monday - 6 for Sunday
     * @param hourStart
     * @param hourEnd
     */
    public void setAvailableOnDay(int day, int hourStart, int hourEnd)
    {
        //technician doesn't work that day
        if (hourStart == 0 && hourEnd == 0)
        {
            getSchedule()[day][0] = hourStart;
            getSchedule()[day][1] = hourEnd;
            return;
        }

        if (day < 0 || day > 6 || hourStart < 0 || hourEnd < 0 || hourEnd > 23 || hourStart >= hourEnd)
        {
            throw new IllegalArgumentException("out of range");
        }

        getSchedule()[day][0] = hourStart;
        getSchedule()[day][1] = hourEnd;
    }

    /**
     * Checks if the technician is available on the given day of the week and hour of the week
     * without checking if the technician has a repair scheduled at that time
     *
     * @return true if the technician is available
     */
    public boolean isNormallyAvailable(int day, int hourOfDay)
    {
        //Argument checks
        if (day < 0 || day > 6 || hourOfDay < 0
                || hourOfDay > 23)
        {
            throw new IllegalArgumentException("out of range");
        }

        boolean isAfterStart = getSchedule()[day][0] <= hourOfDay;
        boolean isBeforeEnd = getSchedule()[day][1] > hourOfDay;
        return isAfterStart && isBeforeEnd;
    }

    /**
     * Checks if the technician is available on the given day of the week
     *
     * @param day The day to check availability.
     * @return true if the technician is available the given day.
     */
    public boolean isDayAvailable(int day)
    {
        //Argument checks
        if (day < 0 || day > 6)
        {
            throw new IllegalArgumentException("out of range");
        }

        boolean isAfterStart = !(getSchedule()[day][0] == 0);
        boolean isBeforeEnd = !(getSchedule()[day][1] == 0);
        return isAfterStart || isBeforeEnd;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Technician that = (Technician) o;
        return Objects.equals(AFM, that.AFM) &&
                Objects.equals(jobs, that.jobs) &&
                Objects.equals(specialty, that.specialty) &&
                Objects.equals(areas, that.areas) &&
                Arrays.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(super.hashCode(), AFM, specialty, areas);
        result = 31 * result + Arrays.hashCode(schedule);
        return result;
    }
}
