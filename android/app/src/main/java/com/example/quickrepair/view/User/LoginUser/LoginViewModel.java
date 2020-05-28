package com.example.quickrepair.view.User.LoginUser;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class LoginViewModel extends ViewModel
{
    LoginPresenter presenter;

    /**
     * Default constructor.
     */
    public LoginViewModel()
    {
        super();

        CustomerDAO customerDAO = new CustomerDAOMemory();
        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new LoginPresenter();
        presenter.setCustomerDAO(customerDAO);
        presenter.setTechnicianDAO(technicianDAO);
    }

    /**
     * Get the presenter associated with the view model.
     *
     * @return The presenter associated with the view model.
     */
    public LoginPresenter getPresenter()
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
