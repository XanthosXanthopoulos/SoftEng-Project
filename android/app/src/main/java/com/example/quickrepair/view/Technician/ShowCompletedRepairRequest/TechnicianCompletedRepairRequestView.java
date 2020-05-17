package com.example.quickrepair.view.Technician.ShowCompletedRepairRequest;

public interface TechnicianCompletedRepairRequestView {

    void showError(String message);

    void setJob(String job);
    void setConsumerName(String consumerName);
    void setAddress(String Address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setEstimatedDuration(String estimatedDuration);
    void setCost(String cost);

}
