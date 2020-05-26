package com.example.quickrepair.view.User.LoginUser;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Technician;

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

    public void login(String username, String password)
    {
        LoginState state = LoginState.NO_ACCOUNT;
        Integer id = null;

        for (Customer customer : customerDAO.findAll())
        {
            if (customer.getUsername().equals(username))
            {
                if (customer.getPassword().equals(password))
                {
                    state = LoginState.VALID_CUSTOMER;
                    id = customer.getUid();
                }
                else
                {
                    state = LoginState.INVALID;
                }

                break;
            }
        }

        if (state == LoginState.NO_ACCOUNT)
        {
            for (Technician technician : technicianDAO.findAll())
            {
                if (technician.getUsername().equals(username))
                {
                    if (technician.getPassword().equals(password))
                    {
                        state = LoginState.VALID_TECHNICIAN;
                        id = technician.getUid();
                    }
                    else
                    {
                        state = LoginState.INVALID;
                    }

                    break;
                }
            }
        }

        switch (state)
        {
            case VALID_CUSTOMER:
                view.OnLoginCustomerSuccess(id);
                break;
            case VALID_TECHNICIAN:
                view.OnLoginTechnicianSuccess(id);
                break;
            default:
                view.showErrorMessage("Invalid credentials", "Invalid username or password.");
                break;
        }
    }

    public void clearView()
    {
        view = null;
    }

    private enum LoginState
    {
        NO_ACCOUNT,
        INVALID,
        VALID_CUSTOMER,
        VALID_TECHNICIAN
    }
}
