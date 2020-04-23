package com.example.quickrepair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private Hashtable<Calendar,ArrayList<RepairRequest>> calendarWithConfirmRequests = new Hashtable<Calendar,ArrayList<RepairRequest>>();

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


    /*
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
    }*/

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
     * Checks if the technician is available on the given day of the week and hour of the week
     * without checking if the technician has a repair scheduled at that time
     *
     * @return true if the technician is available
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

    /**
     * When a technician Confirm a request this are going to added to his calendar
     * @param repairRequest confirm this request
     */
    public void confirmAPendingRequest(RepairRequest repairRequest){
        if(!getPendingRequests().contains(repairRequest)){ throw new IllegalStateException("technician have access onle to requests that exists");}
        //remove it
        getPendingRequests().remove(repairRequest);
        //confirm it
        repairRequest.confirm();

        Calendar actualDate = repairRequest.getConductionDate();
        Calendar newDate = getYearMonthDay(actualDate);

        //add it to the calendarWithConfirmRequests
        if(!calendarWithConfirmRequests.containsKey(newDate)){
            calendarWithConfirmRequests.put(newDate, new ArrayList<RepairRequest>());
            calendarWithConfirmRequests.get(newDate).add(repairRequest);
        }else{
            calendarWithConfirmRequests.get(newDate).add(repairRequest);
        }
    }

    /**
     * Reject request, deleted from set with rairrequests
     * @param repairRequest reject this request
     */
    public void rejectAPendingRequest(RepairRequest repairRequest){
        if(!getPendingRequests().contains(repairRequest)){ throw new IllegalStateException("technician have access onle to requests that exists");}
        getPendingRequests().remove(repairRequest);
    }

    /**
     * add a ready repair to technician's list
     * @param repairRequest ready repair request, with a done repair
     */
    public void readyRepairRequest(RepairRequest repairRequest){
        if(repairRequest.getRepair()==null){ throw new IllegalStateException("only when repair is done");}
        //add it to repair List
        getRepairsList().add(repairRequest.getRepair());
        //delete it from calendarWithConfirmRequests for optimization
        //smaller Set
        Calendar actualDate = repairRequest.getConductionDate();
        Calendar newDate = getYearMonthDay(actualDate);
        if(!calendarWithConfirmRequests.containsKey(newDate)){
            throw new IllegalStateException("at least the current repairRequest for this date");
        }else{
            calendarWithConfirmRequests.get(newDate).remove(repairRequest);
        }

    }

    /**
     * get the day, month, year of a Calendar
     * don't include the hour, minute, second
     * @param actualDate
    */
    private Calendar getYearMonthDay(Calendar actualDate){
        //get the day, month, year
        //don't include the hour
        int day = actualDate.DAY_OF_MONTH;
        int month = actualDate.MONTH;
        int year = actualDate.YEAR;

        // focus on the particular day
        Calendar newDate = Calendar.getInstance();
        newDate.set(Calendar.HOUR_OF_DAY, 0);
        newDate.set(Calendar.MINUTE, 0);
        newDate.set(Calendar.SECOND, 0);
        newDate.set(Calendar.DAY_OF_MONTH, day);
        newDate.set(Calendar.MONTH, month);
        newDate.set(Calendar.YEAR, year);
        return newDate;
    }
    //TODO Add evaluation

}
