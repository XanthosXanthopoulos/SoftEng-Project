package com.example.quickrepair.view.Customer.RegisterCustomer;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Technician;

public class CustomerRegisterPresenter
{
    CustomerDAO customerDAO;
    TechnicianDAO technicianDAO;

    Customer customer;

    CustomerRegisterView view;

    public void registerCustomer(String name, String surname, String phoneNumber, String email, String accountNumber, String username, String password)
    {
        if (customer == null)
        {

            for (Technician technician : technicianDAO.findAll())
            {
                if (technician.getUsername().equals(username))
                {
                    view.showErrorMessage("Username already exist", "This username is already in use by another user.");
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

            customer = new Customer();
        }

        try
        {
            customer.setUserInfo(name, surname, phoneNumber, email, accountNumber, username);
        }
        catch (Exception e)
        {
            view.showErrorMessage("Invalid value", e.getMessage());
            return;
        }

        customer.setPassword(password);

        if (customer.getUid() == 0)
        {
            customer.setUid(customerDAO.nextId());
            customerDAO.save(customer);
        }

        view.onSuccessfulRegister(customer.getUid());
    }

    public void setUpDataSource()
    {
        if (customer != null)
        {
            view.setName(customer.getName());
            view.setSurname(customer.getSurname());
            view.setPhoneNumber(customer.getPhoneNumber());
            view.setBankAccountNumber(customer.getBankAccount());
            view.setEmail(customer.getEmail());
            view.setUsername(customer.getUsername());
            view.setPassword(customer.getPassword());
        }
    }

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public void setCustomer(int customerID)
    {
        customer = customerDAO.find(customerID);
    }

    public void setView(CustomerRegisterView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        view = null;
    }
}
