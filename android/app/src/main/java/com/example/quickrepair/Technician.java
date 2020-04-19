package com.example.quickrepair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Technician extends User
{
    private Set<Job> jobs;

    private List<RepairRequest> pendingRequests = new ArrayList<>();
    private Specialty specialty;
    private List<String> areas = new ArrayList<>();

    //TODO decide if we are going to keep this list
    private List<Repair> repairsList = new ArrayList<>();

    /**
     * Sets the technician personal info performing the necessary checks
     */
    public void setTechnicianInfo(
            String name,
            String surname,
            String phoneNumber,
            String email,
            String bankAccount
    ){
        setUserInfo(name,surname,phoneNumber,email,bankAccount);
    }

    /**
     * Set's this technician's specialty
     */
    public void setSpecialty(Specialty specialty){
        if(specialty == null){
            throw new NullPointerException();
        }
        this.specialty = specialty;
    }
    /**
     * Adds a job to the technician's list of jobs
     */
    public void addJob(Job job){
        if(job == null){
            throw new NullPointerException();
        }
        this.jobs.add(job);
    }

    /**
     *  Adds a repair request to this technicians list
     */
    public void setRepairRequest(RepairRequest repairRequest){
        if(repairRequest == null){
            throw new NullPointerException();
        }
        pendingRequests.add(repairRequest);
    }

    /**
     *  Returns the list of all repairs of this technician completed and in progress
     * @return
     */
    public List<Repair> getRepairs(){
        return repairsList;
    }
}
