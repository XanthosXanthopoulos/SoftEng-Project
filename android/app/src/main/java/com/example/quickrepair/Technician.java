package com.example.quickrepair;

import java.util.ArrayList;
import java.util.List;

public class Technician extends User {
    private List<RepairRequest> pendingRequests = new ArrayList<>();
    private Specialty specialty;
    private List<String> areas;

    //TODO List of repairs and uncompleted repairs
    //private List<Repair> repairsList

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

    public void setSpecialty(Specialty specialty){
        this.specialty = specialty;
    }
    //TODO set job description

    //TODO add job

    public void setRepairRequest(RepairRequest repairRequest){
        pendingRequests.add(repairRequest);
    }
}
