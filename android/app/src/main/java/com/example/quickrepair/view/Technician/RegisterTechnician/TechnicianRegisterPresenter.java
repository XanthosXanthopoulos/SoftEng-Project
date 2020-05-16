package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Customer;

public class TechnicianRegisterPresenter
{
    CustomerDAO customerDAO;
    TechnicianDAO technicianDAO;
    JobTypeDAO jobTypeDAO;

    TechnicianRegisterView view;

    public TechnicianRegisterPresenter() { }

    public void registerTechnician()
    {

    }

    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setJobTypeDAO(JobTypeDAO jobTypeDAO)
    {
        this.jobTypeDAO = jobTypeDAO;
    }

    public void setView(TechnicianRegisterView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        this.view = null;
    }
}
