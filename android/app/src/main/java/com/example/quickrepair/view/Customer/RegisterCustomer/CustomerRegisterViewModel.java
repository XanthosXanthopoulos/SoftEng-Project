package com.example.quickrepair.view.Customer.RegisterCustomer;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class CustomerRegisterViewModel extends ViewModel
{
    CustomerRegisterPresenter presenter;

    /**
     * Default constructor.
     */
    public CustomerRegisterViewModel()
    {
        super();

        CustomerDAO customerDAO = new CustomerDAOMemory();
        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new CustomerRegisterPresenter();
        presenter.setCustomerDAO(customerDAO);
        presenter.setTechnicianDAO(technicianDAO);
    }

    /**
     * Get the presenter associated with the view model.
     *
     * @return The presenter associated with the view model.
     */
    public CustomerRegisterPresenter getPresenter()
    {
        return presenter;
    }

    /**
     * Clear any references to other components.
     */
    @Override
    protected void onCleared()
    {
        super.onCleared();

        presenter.clearView();
    }
}
