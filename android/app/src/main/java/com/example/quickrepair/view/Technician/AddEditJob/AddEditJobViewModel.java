package com.example.quickrepair.view.Technician.AddEditJob;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.memorydao.JobTypeDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;
import com.example.quickrepair.view.Technician.AddEditArea.AddEditAreaPresenter;

public class AddEditJobViewModel extends ViewModel
{
    AddEditJobPresenter presenter;

    /**
     * Default constructor.
     */
    public AddEditJobViewModel()
    {
        super();

        TechnicianDAO technicianDAO = new TechnicianDAOMemory();
        JobTypeDAO jobTypeDAO = new JobTypeDAOMemory();

        presenter = new AddEditJobPresenter();
        presenter.setTechnicianDAO(technicianDAO);
        presenter.setJobTypeDAO(jobTypeDAO);
    }

    /**
     * Get the presenter associated with the view model.
     *
     * @return The presenter associated with the view model.
     */
    public AddEditJobPresenter getPresenter()
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
