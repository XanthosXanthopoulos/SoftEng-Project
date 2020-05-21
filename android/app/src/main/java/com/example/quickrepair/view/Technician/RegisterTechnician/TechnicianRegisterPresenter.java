package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.dao.CustomerDAO;
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
    SpecialtyDAO specialtyDAO;

    Technician technician;

    TechnicianRegisterView view;

    public void setTechnician(int id)
    {
        technician = technicianDAO.find(id);
    }

    public void registerTechnician()
    {
        String name = view.getName();
        String surname = view.getSurname();
        String phoneNumber = view.getPhoneNumber();
        String email = view.getEmail();
        String afm = view.getAFM();
        String accountNumber = view.getAccountNumber();
        Integer specialityID = view.getSpecialityID();
        String username = view.getUsername();
        String password = view.getPassword();

        if (technician == null)
        {

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

            technician = new Technician();
        }

        try
        {
            technician.setTechnicianInfo(name, surname, phoneNumber, email, accountNumber, username);
        }
        catch (Exception e)
        {
            view.showErrorMessage("Invalid value", e.getMessage());
            return;
        }

        Specialty speciality = specialtyDAO.find(specialityID);

        if (speciality == null)
        {
            view.showErrorMessage("No speciality selected", "You must choose a speciality.");
            return;
        }

        technician.setPassword(password);

        if (technician.getSpecialty() == null || technician.getSpecialty().getUid() != specialityID)
        {
            technician.setSpecialty(speciality);
        }

        technician.setAFM(afm);

        if (technician.getUid() == 0)
        {
            technician.setUid(technicianDAO.nextId());
            technicianDAO.save(technician);
        }

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
            specialities.add(speciality.getName().trim());
        }
        view.setSpecialityList(specialities, "Επιλέξτε ειδικότητα");

        if (technician != null)
        {
            view.setName(technician.getName());
            view.setSurname(technician.getSurname());
            view.setEmail(technician.getEmail());
            view.setPhoneNumber(technician.getPhoneNumber());
            view.setAccountNumber(technician.getBankAccount());
            view.setUsername(technician.getUsername());
            view.setPassword(technician.getPassword());
            view.setAFM(technician.getAFM());
            view.setSpecialityID(technician.getSpecialty().getUid());
        }
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
