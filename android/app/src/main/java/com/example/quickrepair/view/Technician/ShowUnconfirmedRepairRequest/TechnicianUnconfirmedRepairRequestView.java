package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

public interface TechnicianUnconfirmedRepairRequestView {

    void reject();
    void confirm();

    void showError(String message);

    void setJob(String job);
    void setConsumerName(String consumerName);
    void setAddress(String Address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setButtonsListeners();

}
