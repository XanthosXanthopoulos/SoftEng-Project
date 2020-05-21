package com.example.quickrepair.view.Customer.RegisterCustomer;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class CustomerRegisterViewModel extends ViewModel
{
    CustomerRegisterPresenter presenter;

    public CustomerRegisterViewModel()
    {
        super();

        CustomerDAO customerDAO = new CustomerDAOMemory();
        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new CustomerRegisterPresenter();
        presenter.setCustomerDAO(customerDAO);
        presenter.setTechnicianDAO(technicianDAO);
    }

    public CustomerRegisterPresenter getPresenter()
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
