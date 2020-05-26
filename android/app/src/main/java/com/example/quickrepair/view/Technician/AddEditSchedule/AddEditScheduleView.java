package com.example.quickrepair.view.Technician.AddEditSchedule;

public interface AddEditScheduleView
{
    void setDay(int day, int start, int end);

    void showErrorMessage(String title, String message);
}
