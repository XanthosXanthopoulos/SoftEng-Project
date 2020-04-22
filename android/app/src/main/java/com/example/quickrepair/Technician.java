package com.example.quickrepair;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    //TODO  Technician constructor

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
}
