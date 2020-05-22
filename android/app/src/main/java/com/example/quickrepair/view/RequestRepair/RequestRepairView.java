package com.example.quickrepair.view.RequestRepair;

import java.util.List;

public interface RequestRepairView {
    /**
     * Display the technicians phone Nummber
     */
    public void setTechnicianPhoneNumber(String text);
    /**
     * Display the technicians name
     */
    public void setTechnicianName(String text);
    /**
     * Displays the job type name
     */
    public void setJobTypeName(String text);
    /**
     * Fills a list with the time ranges that the technician is available
     */
    public void showTimesAvailable(List<String> availableHours);
    /**
     * Shows an error message
     */
    public void showError(String error);
    /**
     * Exits the activity
     */
    public void exit();
    /**
     * Shows an information message
     */
    public void showInfo(String message);

    void submit();

}
