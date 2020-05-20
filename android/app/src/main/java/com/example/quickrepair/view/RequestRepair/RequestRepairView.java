package com.example.quickrepair.view.RequestRepair;

import java.util.List;

public interface RequestRepairView {
    public void setTechnicianPhoneNumber(String text);
    public void setTechnicianName(String text);
    public void setJobTypeName(String text);
    public void showTimesAvailable(List<String> availableHours);
    public void showError(String error);
    public void exit();
    public void showInfo(String message);

}
