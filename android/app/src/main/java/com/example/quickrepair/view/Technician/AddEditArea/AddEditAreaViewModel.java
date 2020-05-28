package com.example.quickrepair.view.Technician.AddEditArea;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.AreaDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.memorydao.AreaDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class AddEditAreaViewModel extends ViewModel
{
    AddEditAreaPresenter presenter;

    /**
     * Default constructor.
     */
    public AddEditAreaViewModel()
    {
        super();

        TechnicianDAO technicianDAO = new TechnicianDAOMemory();
        AreaDAO areaDAO = new AreaDAOMemory();

        presenter = new AddEditAreaPresenter();
        presenter.setTechnicianDAO(technicianDAO);
        presenter.setAreaDAO(areaDAO);
    }

    /**
     * Get the presenter associated with the view model.
     *
     * @return The presenter associated with the view model.
     */
    public AddEditAreaPresenter getPresenter()
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
