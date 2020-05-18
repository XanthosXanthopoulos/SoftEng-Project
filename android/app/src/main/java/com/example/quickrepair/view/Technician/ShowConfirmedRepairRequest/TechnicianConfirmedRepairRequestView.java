package com.example.quickrepair.view.Technician.ShowConfirmedRepairRequest;

public interface TechnicianConfirmedRepairRequestView {
    void showError(String message);

    void complete();

    void setJob(String job);
    void setConsumerName(String consumerName);
    void setAddress(String Address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setEstimatedDuration(String estimatedDuration);
    void setButtonListeners();

}
