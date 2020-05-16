package com.example.quickrepair.view.Customer.RegisterCustomer;

import androidx.lifecycle.ViewModel;

public class CustomerRegisterViewModel extends ViewModel
{
    CustomerRegisterPresenter presenter;

    public CustomerRegisterViewModel()
    {

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
