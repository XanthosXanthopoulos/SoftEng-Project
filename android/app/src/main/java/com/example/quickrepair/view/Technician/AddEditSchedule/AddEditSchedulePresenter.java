package com.example.quickrepair.view.Technician.AddEditSchedule;

import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Schedule;
import com.example.quickrepair.domain.Technician;

import java.util.Calendar;

public class AddEditSchedulePresenter
{
    TechnicianDAO technicianDAO;

    AddEditScheduleView view;

    Technician technician;

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
     * Load the technician in case of edit.
     *
     * @param id The technician's id.
     */
    public void setTechnician(int id)
    {
        technician = technicianDAO.find(id);
    }

    /**
     * Initialize the view.
     */
    public void setUpDataSource()
    {
        for (int i = 0; i < 7; ++i)
        {
            Schedule.ScheduleEntry entry = technician.getSchedule(Calendar.SUNDAY + i);

            view.setDay(i, entry.getStartingHour(), entry.getEndingHour());
        }
    }

    /**
     * Apply the new schedule to the technician.
     *
     * @param schedule The new schedule of the technician.
     */
    public void submit(Integer[][] schedule)
    {
        try
        {
            technician.setSchedule(schedule);
        }
        catch (IllegalArgumentException | NullPointerException e)
        {
            view.showErrorMessage("Invalid value", e.getMessage());
        }
    }

    /**
     * Set the view for the presenter.
     *
     * @param view The view.
     */
    public void setView(AddEditScheduleView view)
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
}
