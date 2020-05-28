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

    /**
     * Try to register a customer with the given information.
     *
     * @param name The name of the customer.
     * @param surname The surname of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param email The email of the customer.
     * @param accountNumber The bank account number of the customer.
     * @param username The username of the customer.
     * @param password The password of the customer.
     */
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
            customer = null;
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

    /**
     * Set the technician DAO for the presenter.
     *
     * @param technicianDAO The technician DAO.
     */
    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    /**
     * Set the customer DAO for the presenter.
     *
     * @param customerDAO The customer DAO.
     */
    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    /**
     * Load the customer in case of edit.
     *
     * @param customerID the customer id.
     */
    public void setCustomer(int customerID)
    {
        customer = customerDAO.find(customerID);
    }

    /**
     * Set the view for the presenter.
     *
     * @param view The view.
     */
    public void setView(CustomerRegisterView view)
    {
        this.view = view;
    }

    /**
     * Clear the view of the presenter.
     */
    public void clearView()
    {
        view = null;
    }
}
