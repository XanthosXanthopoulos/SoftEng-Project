package com.example.quickrepair.view.Technician.AddEditSchedule;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class AddEditScheduleViewModel extends ViewModel
{
    AddEditSchedulePresenter presenter;

    public AddEditScheduleViewModel()
    {
        super();

        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new AddEditSchedulePresenter();
        presenter.setTechnicianDAO(technicianDAO);
    }

    public AddEditSchedulePresenter getPresenter()
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
