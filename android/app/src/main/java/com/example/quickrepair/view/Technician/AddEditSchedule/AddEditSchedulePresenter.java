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

    public void setTechnicianDAO(TechnicianDAO technicianDAO)
    {
        this.technicianDAO = technicianDAO;
    }

    public void setTechnician(int id)
    {
        technician = technicianDAO.find(id);
    }

    public void setUpDataSource()
    {
        for (int i = 0; i < 7; ++i)
        {
            Schedule.ScheduleEntry entry = technician.getSchedule(Calendar.SUNDAY + i);

            view.setDay(i, entry.getStartingHour(), entry.getEndingHour());
        }
    }

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

    public void setView(AddEditScheduleView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        view = null;
    }
}
