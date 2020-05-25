package com.example.quickrepair.view.Technician.AddEditJob;

import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.Technician;

import java.text.NumberFormat;
import java.text.ParseException;
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

    public void addJob(Integer jobTypeID, String price)
    {
        if (jobTypeID == 0)
        {
            view.showErrorMessage("No job selected", "You have to add a valid job.");
            return;
        }

        double priceConverted = 0;
        try
        {
            NumberFormat format = NumberFormat.getInstance();
            priceConverted = format.parse(price).doubleValue();
        }
        catch (ParseException e)
        {
            view.showErrorMessage("Invalid price value", "Please enter a valid price value");
            return;
        }

        if (priceConverted <= 0)
        {
            view.showErrorMessage("Invalid price value", "Price can not be zero or negative.");
            return;
        }

        JobType type = jobTypes.get(jobTypeID - 1);
        Job job = null;

        try
        {
            job = technician.addJob(type, priceConverted);
        }
        catch (IllegalArgumentException e)
        {
            view.showErrorMessage("Invalid value.", e.getMessage());
            return;
        }

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
