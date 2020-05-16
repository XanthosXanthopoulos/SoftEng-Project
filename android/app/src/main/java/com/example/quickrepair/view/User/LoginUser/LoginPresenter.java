package com.example.quickrepair.view.User.LoginUser;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;

public class LoginPresenter
{
    CustomerDAO customerDAO;
    TechnicianDAO technicianDAO;

    LoginView view;

    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setView(LoginView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        view = null;
    }
}
