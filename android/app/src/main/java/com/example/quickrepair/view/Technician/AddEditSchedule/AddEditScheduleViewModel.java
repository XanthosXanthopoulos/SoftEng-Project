package com.example.quickrepair.view.Technician.AddEditSchedule;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class AddEditScheduleViewModel extends ViewModel
{
    AddEditSchedulePresenter presenter;

    /**
     * Default constructor.
     */
    public AddEditScheduleViewModel()
    {
        super();

        TechnicianDAO technicianDAO = new TechnicianDAOMemory();

        presenter = new AddEditSchedulePresenter();
        presenter.setTechnicianDAO(technicianDAO);
    }

    /**
     * Get the presenter associated with the view model.
     *
     * @return The presenter associated with the view model.
     */
    public AddEditSchedulePresenter getPresenter()
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
