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

    public AddEditJobViewModel()
    {
        super();

        TechnicianDAO technicianDAO = new TechnicianDAOMemory();
        JobTypeDAO jobTypeDAO = new JobTypeDAOMemory();

        presenter = new AddEditJobPresenter();
        presenter.setTechnicianDAO(technicianDAO);
        presenter.setJobTypeDAO(jobTypeDAO);
    }

    public AddEditJobPresenter getPresenter()
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
