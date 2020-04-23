package com.example.quickrepair;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Technician extends User
{
    private Set<Job> jobs = new HashSet<>();

    private List<RepairRequest> pendingRequests = new ArrayList<>();
    private Specialty specialty;
    private List<String> areas = new ArrayList<>();

    private List<Repair> repairsList = new ArrayList<>();

    private Calendar[][] schedule = new Calendar[7][2];


    public Technician(String name, String surname, String phoneNumber, String email,
                      String bankAccount, String username, String password, Specialty specialty)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        setSpecialty(specialty);
    }

    /**
     * Sets the technician personal info performing the necessary checks
     */
    public void setTechnicianInfo(String name, String surname, String phoneNumber, String email, String bankAccount, String username)
    {
        setUserInfo(name, surname, phoneNumber, email, bankAccount, username);
    }

    /**
     * Set's this technician's specialty
     */
    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        this.specialty = specialty;
    }

    /**
     * Adds a job to the technician's list of jobs
     */
    public void addJob(Job job)
    {
        if (job == null) throw new NullPointerException("Job can not be null.");

        this.jobs.add(job);
    }

    /**
     * Adds a repair request to this technicians list
     */
    public void addRepairRequest(RepairRequest repairRequest)
    {
        if (repairRequest == null) throw new NullPointerException();

        pendingRequests.add(repairRequest);
    }

    public Set<Job> getJobs()
    {
        return jobs;
    }

    public List<RepairRequest> getPendingRequests()
    {
        return pendingRequests;
    }

    public Specialty getSpecialty()
    {
        return specialty;
    }

    //TODO Convert areas to hashset and
    public List<String> getAreas()
    {
        return areas;
    }

    /**
     * Returns the list of all repairs of this technician completed and in progress
     *
     * @return
     */
    public List<Repair> getRepairsList()
    {
        return repairsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<LocalDateTime> getAvailableDate(LocalDateTime dateTime, Job job)
    {
        ArrayList<LocalDateTime> availableDates = new ArrayList<>();

        for (int i = 0; i < pendingRequests.size() - 1; ++i)
        {
            if (pendingRequests.get(i).getConductionDate().getDayOfYear() == dateTime.getDayOfYear())
            {
                if (pendingRequests.get(i).getConductionDate().getDayOfYear() == pendingRequests.get(i + 1).getConductionDate().getDayOfYear())
                {
                    if (pendingRequests.get(i + 1).getConductionDate().getMinute() - pendingRequests.get(i).getConductionDate().getMinute() - pendingRequests.get(i).getJob().getDuration() >= job.getDuration())
                    {
                        availableDates.add(pendingRequests.get(i).getConductionDate().plusMinutes(job.getDuration()));
                        availableDates.add(pendingRequests.get(i + 1).getConductionDate());
                    }
                }
            }
        }

        return availableDates;
    }

    /**
     * Marks this technician as available the given day of the week from @param hourStart until
     * @param hourEnd
     *
     * Ideal
     * Example usage :  setAvailableOnDay(Calendar.MONDAY , 15 , 20)  to mark a technician available
     * on monday from 15:00 to 20:00
     *
     * Current
     * Example usage :  setAvailableOnDay(Calendar.0 , 15 , 20)  to mark a technician available
     *      * on monday from 15:00 to 20:00
     * @param day
     * @param hourStart
     * @param hourEnd
     */
    public void setAvailableOnDay(int day , int hourStart , int hourEnd){
        //Argument checks
        if(day < 0 || day > 6 || hourStart < 0
                || hourStart > 23 || hourEnd < 0 || hourEnd > 23 || hourStart >= hourEnd){
            throw new IllegalArgumentException();
        }
        getSchedule()[day][0].set(Calendar.HOUR_OF_DAY , hourStart);
        getSchedule()[day][1].set(Calendar.HOUR_OF_DAY , hourEnd);
    }

    /**
     * Checks if the technician is available on the given day of the week and hour of the week
     * without checking if the technician has a repair scheduled at that time
     *
     * @return true if the technician is available
     */
    public boolean isNormallyAvailable(int day , int hourOfDay){
        //Argument checks
        if(day < 0 || day > 6 || hourOfDay < 0
                || hourOfDay > 23 ){
            throw new IllegalArgumentException();
        }

        boolean isAfterStart = getSchedule()[day][0].get(Calendar.HOUR_OF_DAY) <= hourOfDay;
        boolean isBeforeEnd = getSchedule()[day][1].get(Calendar.HOUR_OF_DAY) > hourOfDay;
        return  isAfterStart && isBeforeEnd;
    }


    public Calendar[][] getSchedule()
    {
        return schedule;
    }

    /**
     *  Sets the technicians schedule performing the necessary checks
     * @param schedule
     */
    public void setSchedule(Calendar[][] schedule)
    {
        if(schedule == null){
            throw new NullPointerException("null schedule");
        }
        if(schedule.length != 7){
            throw new IllegalArgumentException("The schedule must have 7 entries");
        }
        for(int i = 0 ; i < 7 ; i ++){
            //for(int j = 0 ; j < 7 ; j ++) {
            //    System.err.println(getSchedule()[j][0]);
            //    System.err.println(getSchedule()[j][1]);
            //}
            //If the entry is null then it means the technician is available all day
            if(schedule[i][0] == null || schedule[i][1] == null){
                throw new NullPointerException("null schedule entries on " + i);
            }
            if(schedule[i] != null && schedule[i].length != 2){
                throw new IllegalArgumentException("Every entry must  have 2 calendars");
            }
        }
        this.schedule = schedule;
    }
}
