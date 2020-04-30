package com.example.quickrepair.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Technician extends User
{

    private String AFM;
    private Set<Job> jobs = new HashSet<>();
    private List<RepairRequest> repairRequests = new ArrayList<>();
    private Specialty specialty;
    private HashSet<String> areas = new HashSet<>();

    //This structure will set up by the technician
    //it is necessary for a costumer to know when the technician is on duty
    //for each day of the week -> (startHour, endHour)
    private Integer[][] schedule = new Integer[7][2];

    // Day,Month,Year -> Array with confirmed repairs
    //this structure will help Technician to organize his repairs and his schedule
    //and will give the necessary information to the costumer when a Technician is free
    private Hashtable<GregorianCalendar, ArrayList<RepairRequest>> calendarWithConfirmRequests = new Hashtable<>();

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
    //TODO input hours of day can be set until 24 if the technician can literally work all dayt
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

    //GETTERS
    //TODO: comments to getters
    public Set<Job> getJobs()
    {
        return jobs;
    }

    public List<RepairRequest> getRepairRequests()
    {
        return repairRequests;
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

    public Hashtable<GregorianCalendar, ArrayList<RepairRequest>> getCalendarWithConfirmRequests()
    {
        return calendarWithConfirmRequests;
    }

    //Add methods
    //Hashtable<GregorianCalendar, ArrayList<RepairRequest>> calendarWithConfirmRequests
    public void notifyWithConfirmation(RepairRequest repairRequest)
    {
        GregorianCalendar actualDate = repairRequest.getConductionDate();
        GregorianCalendar newDate = Utilities.getYearMonthDay(actualDate);

        //add it to the calendarWithConfirmRequests
        if (!calendarWithConfirmRequests.containsKey(newDate))
        {
            calendarWithConfirmRequests.put(newDate, new ArrayList<RepairRequest>());
            calendarWithConfirmRequests.get(newDate).add(repairRequest);
        }
        else
        {
            calendarWithConfirmRequests.get(newDate).add(repairRequest);
        }
    }

    /**
     * Adds a job to the technician's list of jobs
     * Also updates the association references
     */
    public Job addJob(JobType jobType, double price, int duration)
    {
        if (!jobType.getSpecialty().equals(getSpecialty()))
        {
            throw new IllegalArgumentException("A technician can only offer jobs from his specialty");
        }
        Job job = new Job(this, jobType, price, duration);
        this.jobs.add(job);
        job.getJobType().addJob(job);
        return job;
    }

    /**
     * Removes a job from the technicians list of job
     * Also updates the list of jobs that offer the job's jobtype
     */
    public void removeJob(Job job)
    {
        if (job == null)
        {
            throw new NullPointerException();
        }
        getJobs().remove(job);
        job.getJobType().removeJob(job);
    }

    /**
     * Adds a repair request to this technicians list
     */
    public void addRepairRequest(RepairRequest repairRequest)
    {
        if (repairRequest == null) throw new NullPointerException();

        repairRequests.add(repairRequest);
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
     * Returns a set of all the evaluations that have been done for this technician
     *
     * @return
     */
    public Set<Evaluation> getEvaluations()
    {
        Set<Evaluation> evaluations = new HashSet<>();

        for (RepairRequest repairRequest : repairRequests)
        {
            if (repairRequest.isCompleted())
            {
                //The customer may have refused to evaluate the technician
                if (repairRequest.getRepair().getEvaluation() != null)
                {
                    evaluations.add(repairRequest.getRepair().getEvaluation());
                }
            }
        }
        return evaluations;
    }

    public ArrayList<ArrayList<GregorianCalendar>> getAvailableHourRanges(GregorianCalendar date)
    {
        int dayOfWeek = date.get(date.DAY_OF_WEEK);
        if (!isDayAvailable(dayOfWeek - 1))
        {
            return null;
        }

        int start = schedule[dayOfWeek - 1][0];
        int end = schedule[dayOfWeek - 1][1];

        GregorianCalendar newDate = Utilities.getYearMonthDay(date);
        ArrayList<RepairRequest> repairRequests = calendarWithConfirmRequests.get(newDate);

        ArrayList<ArrayList<GregorianCalendar>> gaps = new ArrayList<>();

        if (repairRequests == null || repairRequests.size() == 0)
        {
            //only one gap, he is free all day
            ArrayList<GregorianCalendar> gap = new ArrayList<>();
            GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), start, 0);
            GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), end, 0);
            gap.add(startCal);
            gap.add(endCal);
            gaps.add(gap);
        }
        else
        {
            //create gaps
            Collections.sort(repairRequests);

            //first gap
            RepairRequest firstRepairRequest = repairRequests.get(0);
            GregorianCalendar firstRepairReqCalendar = (GregorianCalendar) firstRepairRequest.getConductionDate().clone();

            int hour = firstRepairReqCalendar.get(firstRepairReqCalendar.HOUR_OF_DAY);
            int min = firstRepairReqCalendar.get(firstRepairReqCalendar.MINUTE);

            if (hour != start || min != 0)
            {
                ArrayList<GregorianCalendar> gap = new ArrayList<>();
                GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), start, 0);
                GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), hour, min);
                gap.add(startCal);
                gap.add(endCal);
                gaps.add(gap);
            }

            RepairRequest beforeRepairRequest = firstRepairRequest;
            GregorianCalendar beforeRepairReqCalendar = firstRepairReqCalendar;

            for (int i = 1; i < repairRequests.size(); i++)
            {
                //next step
                RepairRequest nextRepairRequest = repairRequests.get(i);
                GregorianCalendar nextRepairReqCalendar = (GregorianCalendar) nextRepairRequest.getConductionDate().clone();
                beforeRepairReqCalendar.add(beforeRepairReqCalendar.MINUTE, beforeRepairRequest.getEstimatedDuration());

                if (beforeRepairReqCalendar.before(nextRepairReqCalendar))
                {
                    //new gap
                    ArrayList<GregorianCalendar> gap = new ArrayList<>();
                    GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR_OF_DAY), beforeRepairReqCalendar.get(beforeRepairReqCalendar.MINUTE));
                    GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), nextRepairReqCalendar.get(nextRepairReqCalendar.HOUR_OF_DAY), nextRepairReqCalendar.get(nextRepairReqCalendar.MINUTE));
                    gap.add(startCal);
                    gap.add(endCal);
                    gaps.add(gap);
                }

                //else no gap between these repairRequests
                beforeRepairRequest = nextRepairRequest;
                beforeRepairReqCalendar = nextRepairReqCalendar;
            }
            beforeRepairReqCalendar.add(beforeRepairReqCalendar.MINUTE, beforeRepairRequest.getEstimatedDuration());
            //last gap
            if (beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR_OF_DAY) < end)
            {
                //create gap
                //new gap
                ArrayList<GregorianCalendar> gap = new ArrayList<>();
                GregorianCalendar startCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), beforeRepairReqCalendar.get(beforeRepairReqCalendar.HOUR_OF_DAY), beforeRepairReqCalendar.get(beforeRepairReqCalendar.MINUTE));
                GregorianCalendar endCal = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DAY_OF_MONTH), end, 0);
                gap.add(startCal);
                gap.add(endCal);
                gaps.add(gap);
            }
        }
        return gaps;
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
     * Checks if the technician offers a job for less than a given price
     *
     * @param jobType the jobtype being teste
     * @param price   the maximum price the technician offers the job for
     * @return true if he offers the jobtype for less than price
     */
    public boolean offersJobForLessThanPrice(JobType jobType, double price)
    {
        for (Job job : getJobs())
        {
            if (job.getJobType().equals(jobType))
            {
                if (job.getPrice() < price) return true;
            }
        }
        return false;
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
        //Argument checks
        if (day < 0 || day > 6 || hourStart < 0
                || hourStart > 23 || hourEnd < 0 || hourEnd > 23 || hourStart >= hourEnd)
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technician that = (Technician) o;
        return AFM.equals(that.AFM);
    }

    @Override
    public int hashCode()
    {
        return AFM.hashCode();
    }
    //TODO Equals
}
