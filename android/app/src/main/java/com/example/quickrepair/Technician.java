package com.example.quickrepair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Technician extends User
{

    private Set<Job> jobs = new HashSet<>();

    private List<RepairRequest> pendingRequests = new ArrayList<>();

    private Specialty specialty;

    private HashSet<String> areas = new HashSet<String>();

    private List<Repair> repairsList = new ArrayList<>();

    //This structure will set up by the technician
    //it is necessary for a costumer to know when the technician is on duty
    //for each day of the week -> (startHour, endHour)
    private Integer[][] schedule = new Integer[7][2];

    // Day,Month,Year -> Array with confirmed repairs
    //this structure will help Technician to organize his repairs and his schedule
    //and will give the necessary information to the costumer when a Technician is free
    private Hashtable<GregorianCalendar,ArrayList<RepairRequest>> calendarWithConfirmRequests = new Hashtable<GregorianCalendar,ArrayList<RepairRequest>>();

    // Constructors
    public Technician(String name, String surname, String phoneNumber, String email,
                      String bankAccount, String username, String password, Specialty specialty)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        setSpecialty(specialty);
    }

    public Technician(String name, String surname, String phoneNumber, String email,
                      String bankAccount, String username, String password, Specialty specialty,HashSet<String> areas)
    {
        super(name, surname, phoneNumber, email, bankAccount, username, password);
        setSpecialty(specialty);
        setAreas(areas);
    }

    //SETTERS
    //TODO: Comments and checks at setters
    /**
     * Set's this technician's specialty
     * @param specialty Technician's specialty
     */
    public void setSpecialty(Specialty specialty)
    {
        if (specialty == null) throw new NullPointerException("Speciality can not be null.");

        this.specialty = specialty;
    }

    /**
     *
     * @param repairsList
     */
    public void setRepairsList(List<Repair> repairsList) {
        this.repairsList = repairsList;
    }
    /**
     *
     * @param jobs
     */
    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public void setAreas(HashSet<String> areas) {
        this.areas = areas;
    }

    public void setPendingRequests(List<RepairRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
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
    //TODO input hours of day can be set until 24 if the technician can literally work al ldayt
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
                throw new IllegalArgumentException("Every entry must  have 2 calendars");
            }
            if (schedule[i][0] == null || schedule[i][1] == null)
            {
                throw new NullPointerException("null schedule entries on " + i);
            }

        }

        this.schedule = schedule;
    }


    //GETTERS
    //TODO: comments to getters
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

    public List<Repair> getRepairsList() {
        return repairsList;
    }

    public HashSet<String> getAreas()
    {
        return areas;
    }

    public Integer[][] getSchedule()
    {
        return schedule;
    }

    //Add methods

    /**
     * Adds a job to the technician's list of jobs
     */
    //TODO Job can be added  only if the technician has the jobs specialty
    public void addJob(Job job)
    {
        if (job == null) throw new NullPointerException("Job can not be null.");

        this.jobs.add(job);
    }

    /**
     * Adds a repair request to this technicians list
     */
    //TODO Check if the technician can service the repairRequest
    public void addRepairRequest(RepairRequest repairRequest)
    {
        if (repairRequest == null) throw new NullPointerException();

        pendingRequests.add(repairRequest);
    }
    //TODO Check for null maybe
    public void addNewArea(String area){
    }

    public ArrayList<LocalDateTime> getAvailableDate(LocalDateTime dateTime, Job job)
    {
        ArrayList<LocalDateTime> availableDates = new ArrayList<>();
        /*
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
        }*/

        return availableDates;
    }

    /*
    *
     */
    public ArrayList<ArrayList<GregorianCalendar>> getAvailableHours(GregorianCalendar date, Job job){
        int dayOFWeek = date.DAY_OF_WEEK;
        //technician doesn't work this day
        if(isDayAvailable(dayOFWeek-1)){return null;}
        if(!this.getJobs().contains(job)){ throw new IllegalArgumentException("costumer have access only to technicians with a particular job"); }
        int start = getSchedule()[dayOFWeek-1][0];
        int end = getSchedule()[dayOFWeek-1][0];
        GregorianCalendar newDate = Utilities.getYearMonthDay(date);
        ArrayList<RepairRequest> repairRequests = calendarWithConfirmRequests.get(newDate);

        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<ArrayList<GregorianCalendar>>();

        if(repairRequests ==null || repairRequests.size()==0){
            //only one gap, he is free all day
            ArrayList<GregorianCalendar> gap = new ArrayList<GregorianCalendar>();
            GregorianCalendar startCal = new GregorianCalendar(date.YEAR, date.MONTH,date.DAY_OF_MONTH, start ,0);
            GregorianCalendar endCal = new GregorianCalendar(date.YEAR, date.MONTH,date.DAY_OF_MONTH, end ,0);
            gap.add(startCal);
            gap.add(endCal);
            gaps.add(gap);
        }else{
            //create gaps
            Collections.sort(repairRequests);

            //first gap
            RepairRequest firstRepairRequest = repairRequests.get(0);
            GregorianCalendar firstRepairReqCalendar = (GregorianCalendar)firstRepairRequest.getConductionDate().clone();

            int hour = firstRepairReqCalendar.get(firstRepairReqCalendar.HOUR);
            int min = firstRepairReqCalendar.get(firstRepairReqCalendar.MINUTE);

            if(!(hour == start && min == 0)){
                ArrayList<GregorianCalendar> gap = new ArrayList<GregorianCalendar>();
                GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH) , start ,0);
                GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH) , hour ,min);
                gap.add(startCal);
                gap.add(endCal);
                gaps.add(gap);
            }
            RepairRequest beforeRepairRequest = firstRepairRequest;
            GregorianCalendar beforeRepairReqCalendar = firstRepairReqCalendar;

            for(int i = 1; i<repairRequests.size(); i++){
                //next step
                RepairRequest nextRepairRequest = repairRequests.get(i);
                GregorianCalendar nextRepairReqCalendar = (GregorianCalendar)nextRepairRequest.getConductionDate().clone();

                beforeRepairReqCalendar.add(beforeRepairReqCalendar.MINUTE, beforeRepairRequest.getEstimatedDuration());
                if(beforeRepairReqCalendar.compareTo(nextRepairReqCalendar) < 0 ){
                    //new gap
                    ArrayList<GregorianCalendar> gap = new ArrayList<GregorianCalendar>();
                    GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH), beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR), beforeRepairReqCalendar.get(beforeRepairReqCalendar.MINUTE));
                    GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH) , nextRepairReqCalendar.get(nextRepairReqCalendar.HOUR) , nextRepairReqCalendar.get(nextRepairReqCalendar.MINUTE));
                    gap.add(startCal);
                    gap.add(endCal);
                    gaps.add(gap);
                }//else no gap between these repairRequests
                beforeRepairRequest = nextRepairRequest;
                beforeRepairReqCalendar = nextRepairReqCalendar;
            }

            beforeRepairReqCalendar.add(beforeRepairReqCalendar.MINUTE, beforeRepairRequest.getEstimatedDuration());
            //last gap
            if (beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR) < end ) {
                //create gap
                //new gap
                ArrayList<GregorianCalendar> gap = new ArrayList<GregorianCalendar>();
                GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH), beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR), beforeRepairReqCalendar.get(beforeRepairReqCalendar.MINUTE));
                GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH),date.get(date.DAY_OF_MONTH) , end , 0);
                gap.add(startCal);
                gap.add(endCal);
                gaps.add(gap);
            }
        }
        return gaps;
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
     * @param day 0 for Monday - 6 for Sunday
     * @param hourStart
     * @param hourEnd
     */
    public void setAvailableOnDay(int day, int hourStart, int hourEnd)
    {
        //Argument checks
        if (day < 0 || day > 6 || hourStart < 0
                || hourStart > 23 || hourEnd < 0 || hourEnd > 23 || hourStart >= hourEnd)
        {
            throw new IllegalArgumentException("out of range");
        }
        getSchedule()[day][0]= hourStart;
        getSchedule()[day][1]= hourEnd;
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
     * @param day
     * @return true if the technician is available the given day
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

}
