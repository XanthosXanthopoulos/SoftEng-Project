package com.example.quickrepair.view.Technician.RegisterTechnician;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.SpecialtyDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.SpecialtyDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class TechnicianRegisterViewModel extends ViewModel
{
    TechnicianRegisterPresenter presenter;

    public TechnicianRegisterViewModel()
    {
        super();

        CustomerDAO customerDAO = new CustomerDAOMemory();
        TechnicianDAO technicianDAO = new TechnicianDAOMemory();
        SpecialtyDAO specialtyDAO = new SpecialtyDAOMemory();

        presenter = new TechnicianRegisterPresenter();
        presenter.setCustomerDAO(customerDAO);
        presenter.setTechnicianDAO(technicianDAO);
        presenter.setSpecialtyDAO(specialtyDAO);
    }

    public TechnicianRegisterPresenter getPresenter()
    {
        return presenter;
    }

    @Override
    protected void onCleared()
    {
        super.onCleared();

        presenter.clearView();
    }
}
