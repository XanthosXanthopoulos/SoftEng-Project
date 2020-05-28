package com.example.quickrepair.view.Technician.AddEditSchedule;

public interface AddEditScheduleView
{
    /**
     * Set the starting and ending hour of the given day.
     *
     * @param day The day to set the hours.
     * @param start The starting hour.
     * @param end The ending hour.
     */
    void setDay(int day, int start, int end);

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    void showErrorMessage(String title, String message);
}
