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

    /**
     * Set the technician DAO for the presenter.
     *
     * @param technicianDAO The technician DAO.
     */
    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    /**
     * Set the area DAO for the presenter.
     *
     * @param areaDAO The area DAO.
     */
    public void setAreaDAO(AreaDAO areaDAO)
    {
        this.areaDAO = areaDAO;
    }

    /**
     * Set the view for the presenter.
     *
     * @param view The view.
     */
    public void setView(AddEditAreaView view)
    {
        this.view = view;
    }

    /**
     * Clear the view of the presenter.
     */
    public void clearView()
    {
        view = null;
    }

    /**
     * Load the technician in case of edit.
     *
     * @param id The technician's id.
     */
    public void setTechnician(Integer id)
    {
        technician = technicianDAO.find(id);
    }

    /**
     * Initialize the view.
     */
    public void setUpDataSource()
    {
        view.setAreaList(new ArrayList<>(areaDAO.getAreas()), "Επιλέξτε περιοχή");

        areas = new ArrayList<>(technician.getAreas());
        view.setSelectedArea(areas);
    }

    /**
     * Add a new area to the technician.
     *
     * @param areaID the area id.
     */
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

    /**
     * Remove an area from the technician.
     *
     * @param areaID The area's id to be removed.
     */
    public void removeArea(int areaID)
    {
        technician.getAreas().remove(areas.remove(areaID));
        view.setSelectedArea(areas);
    }
}
