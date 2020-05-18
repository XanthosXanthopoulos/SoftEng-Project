package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.SpecialtyDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import java.util.ArrayList;

public class TechnicianRegisterPresenter
{
    CustomerDAO customerDAO;
    TechnicianDAO technicianDAO;
    JobTypeDAO jobTypeDAO;
    SpecialtyDAO specialtyDAO;

    TechnicianRegisterView view;

    public TechnicianRegisterPresenter()
    {

    }

    public void registerTechnician()
    {
        String name = view.getName();
        String surname = view.getSurname();
        String phoneNumber = view.getPhoneNumber();
        String email = view.getEmail();
        String afm = view.getAFM();
        String accountNumber = view.getAccountNumber();
        Integer speciality = view.getSpecialityID();
        String username = view.getUsername();
        String password = view.getPassword();

        for (Technician technician : technicianDAO.findAll())
        {
            if (technician.getUsername().equals(username))
            {
                view.showErrorMessage("Username already exist", "This username is already in use by another user.");
                return;
            }
            else if (technician.getAFM().equals(afm))
            {
                view.showErrorMessage("Duplicate AFM", "Please make sure you do not have already an account and you have typed the correct AFM.");
                return;
            }
        }

        for (Customer customer : customerDAO.findAll())
        {
            if (customer.getUsername().equals(username))
            {
                view.showErrorMessage("Username already exist", "This username is already in use by another user.");
                return;
            }
        }

        Technician technician = new Technician();
        technician.setTechnicianInfo(name, surname, phoneNumber, email, accountNumber, username);
        technician.setPassword(password);
        System.out.println("Spec: " + speciality);
        technician.setSpecialty(specialtyDAO.find(speciality));
        technician.setAFM(afm);

        technician.setUid(technicianDAO.nextId());
        technicianDAO.save(technician);
        view.onSuccessfulRegister(technician.getUid());
    }

    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setSpecialtyDAO(SpecialtyDAO specialtyDAO)
    {
        this.specialtyDAO = specialtyDAO;
    }

    public void setUpDataSource()
    {
        ArrayList<String> specialities = new ArrayList<>();
        for (Specialty speciality : specialtyDAO.findAll())
        {
            specialities.add(speciality.getName());
        }
        view.setSpecialityList(specialities, "Επιλέξτε ειδικότητα");
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
