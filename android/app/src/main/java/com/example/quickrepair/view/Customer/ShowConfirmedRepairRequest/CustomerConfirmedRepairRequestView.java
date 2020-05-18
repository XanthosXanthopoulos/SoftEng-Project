package com.example.quickrepair.view.Customer.ShowConfirmedRepairRequest;

public interface CustomerConfirmedRepairRequestView {
    void showError(String message);

    void setJob(String job);
    void setTechnicianName(String technicianName);
    void setAddress(String address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setEstimatedDuration(String estimatedDuration);
}
