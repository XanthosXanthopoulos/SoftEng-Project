package com.example.quickrepair.view.Technician.RegisterTechnician;

import androidx.lifecycle.ViewModel;

public class TechnicianRegisterViewModel extends ViewModel
{
    TechnicianRegisterPresenter presenter;

    public TechnicianRegisterViewModel()
    {
        super();

        //TODO: Initialize DAO for assembling Presenter

        presenter = new TechnicianRegisterPresenter();
    }

    public TechnicianRegisterPresenter getPresenter()
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
