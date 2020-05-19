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
    ArrayList<Job> jobs;
    ArrayList<JobType> jobTypes;

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
        jobTypes = new ArrayList<>(technician.getSpecialty().getJobTypes());
    }

    public void setUpDataSource()
    {
        ArrayList<String> jobTypeNames = new ArrayList<>();

        for (JobType jobType : technician.getSpecialty().getJobTypes())
        {
            jobTypeNames.add(jobType.getName());
        }
        view.setJobTypeList(jobTypeNames, "Επιλέξτε εργασία");

        jobs = new ArrayList<>(technician.getJobs());
        view.setJobList(jobs);
    }

    public void addJob(Integer jobTypeID, double price)
    {
        if (jobTypeID == 0) view.showErrorMessage("No job selected", "You have to add a valid job.");

        JobType type = jobTypes.get(jobTypeID - 1);

        Job job = technician.addJob(type, price);
        jobs.add(job);
        view.setJobList(jobs);
    }

    public void removeJob(int jobPosition)
    {
        technician.removeJob(jobs.remove(jobPosition));
        view.setJobList(jobs);
    }

    public void clearView()
    {
        view = null;
    }
}
