package com.example.quickrepair.view.RequestRepair;

import java.util.List;

public interface RequestRepairView
{
    /**
     * Display the technicians phone number
     */
    void setTechnicianPhoneNumber(String text);

    /**
     * Display the technicians name
     */
    void setTechnicianName(String text);

    /**
     * Displays the job type name
     */
    void setJobTypeName(String text);

    /**
     * Fills a list with the time ranges that the technician is available
     */
    void showTimesAvailable(List<String> availableHours);

    /**
     * Shows an error message
     */
    void showError(String error);

    /**
     * Return to the customer home page.
     */
    void submit();

}
