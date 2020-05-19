package com.example.quickrepair.view.Technician.AddEditJob;

import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.Technician;

import java.util.ArrayList;

public class AddEditJobPresenter
{
    TechnicianDAO technicianDAO;
    JobTypeDAO jobTypeDAO;

    AddEditJobView view;

    Technician technician;

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setJobTypeDAO(JobTypeDAO jobTypeDAO)
    {
        this.jobTypeDAO = jobTypeDAO;
    }

    public void setView(AddEditJobView view)
    {
        this.view = view;
    }

    public void setTechnician(Integer id)
    {
        technician = technicianDAO.find(id);
    }

    public void setUpDataSource()
    {
        ArrayList<String> jobTypes = new ArrayList<>();

        for (JobType jobType : technician.getSpecialty().getJobTypes())
        {
            jobTypes.add(jobType.getName());
        }
        view.setJobTypeList(jobTypes, "Επιλέξτε εργασία");

        view.setJobList(new ArrayList<>(technician.getJobs()));
    }

    public void addJob(Integer jobTypeID, double price)
    {
        JobType type = jobTypeDAO.find(jobTypeID);

        technician.addJob(type, price);
        view.setJobList(new ArrayList<>(technician.getJobs()));
    }

    public void clearView()
    {
        view = null;
    }
}
