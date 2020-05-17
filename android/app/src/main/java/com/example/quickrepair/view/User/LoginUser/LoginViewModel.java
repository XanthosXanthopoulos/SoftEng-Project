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

    public LoginViewModel()
    {
        super();

        CustomerDAO customerDAO = new CustomerDAOMemory();
        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new LoginPresenter();
        presenter.setCustomerDAO(customerDAO);
        presenter.setTechnicianDAO(technicianDAO);
    }

    public LoginPresenter getPresenter()
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
