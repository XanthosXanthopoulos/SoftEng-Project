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
    ArrayList<String> areas;

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
        view.setAreaList(new ArrayList<>(areaDAO.getAreas()), "Επιλέξτε περιοχή");

        areas = new ArrayList<>(technician.getAreas());
        view.setSelectedArea(areas);
    }

    public void addArea(Integer areaID)
    {
        if (areaID == 0)
        {
            view.showErrorMessage("No area selected", "You have to add a valid area.");
            return;
        }

        String area = areaDAO.getAreas().get(areaID - 1);

        if (technician.getAreas().contains(area))
        {
            view.showErrorMessage("Area already exist", "This area is already selected.");
            return;
        }
        else
        {
            technician.getAreas().add(area);
        }

        areas.add(area);
        view.setSelectedArea(areas);
    }

    public void removeArea(int areaID)
    {
        technician.getAreas().remove(areas.remove(areaID));
        view.setSelectedArea(areas);
    }
}
