package com.example.quickrepair.view.Technician.AddEditArea;

import com.example.quickrepair.dao.AreaDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;

import java.util.ArrayList;

public class AddEditAreaPresenter
{
    AddEditAreaView view;

    TechnicianDAO technicianDAO;
    AreaDAO areaDAO;

    Technician technician;

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setAreaDAO(AreaDAO areaDAO)
    {
        this.areaDAO = areaDAO;
    }

    public void setView(AddEditAreaView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        view = null;
    }

    public void setTechnician(Integer id)
    {
        technician = technicianDAO.find(id);
    }

    public void setUpDataSource()
    {
        view.setAreaList(areaDAO.getAreas(), "Επιλέξτε ειδικότητα");

        view.setSelectedArea(new ArrayList<String>(technician.getAreas()));
    }

    public void addArea(Integer areaID)
    {
        String area = areaDAO.getAreas().get(areaID);

        if (technician.getAreas().contains(area))
        {
            view.showErrorMessage("Area already exist", "This area is already selected.");
        }
        else
        {
            technician.getAreas().add(area);
        }
    }

    public void removeArea(String area)
    {
        technician.getAreas().remove(area);
    }
}
