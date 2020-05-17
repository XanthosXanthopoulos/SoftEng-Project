package com.example.quickrepair.view.Customer.RegisterCustomer;

public class CustomerRegisterPresenter
{
    CustomerRegisterView view;

    public CustomerRegisterPresenter() { }

    public void setView(CustomerRegisterView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        view = null;
    }
}
