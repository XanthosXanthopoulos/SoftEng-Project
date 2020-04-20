package com.example.quickrepair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Technician extends User
{
    private String AFM;

    private Specialty specialty;
    private Set<Job> jobs;

    private List<RepairRequest> pendingRequests = new ArrayList<>();
    private List<String> areas = new ArrayList<>();

    //TODO decide if we are going to keep this list
    private List<Repair> repairsList = new ArrayList<>();

    /**
     * Sets the technician personal info performing the necessary checks
     */
    public void setTechnicianInfo(String name, String surname, String phoneNumber, String email, String bankAccount)
    {
        setUserInfo(name,surname,phoneNumber,email,bankAccount);
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
        if(job == null) throw new NullPointerException("Job can not be null.");

        this.jobs.add(job);
    }

    /**
     *  Adds a repair request to this technicians list
     */
    public void addRepairRequest(RepairRequest repairRequest)
    {
        if(repairRequest == null) throw new NullPointerException("Repair request can not be null.");

        pendingRequests.add(repairRequest);
    }

    /**
     *  Returns the list of all repairs of this technician completed and in progress
     * @return
     */
    public List<Repair> getRepairs()
    {
        return new ArrayList<>(repairsList);
    }

    public Set<Job> getJobs()
    {
        return new HashSet<>(jobs);
    }

    public Specialty getSpecialty()
    {
        return specialty;
    }

    public ArrayList<LocalDateTime> getAvailability(Job job, LocalDate date)
    {
        ArrayList<LocalDateTime> availableDates = new ArrayList<>();

        for (int i = 0; i < pendingRequests.size() - 1; ++i)
        {
            if (pendingRequests.get(i).getConductionDate().getDayOfYear() == pendingRequests.get(i + 1).getConductionDate().getDayOfYear() && pendingRequests.get(i + 1).getConductionDate().getDayOfYear() == date.getDayOfYear())
            {
                if (pendingRequests.get(i + 1).getConductionDate().getMinute() - pendingRequests.get(i).getConductionDate().getMinute() >= job.getDuration())
                {
                    //TODO: Put into list minus the job duration
                }
            }
        }

        return availableDates;
    }
}
