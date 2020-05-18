package com.example.quickrepair.view.Customer.ShowUnconfirmedRepairRequest;

public interface CustomerUnconfirmedRepairRequestView {

    void showError(String message);

    void setJob(String job);
    void setTechnicianName(String technicianName);
    void setAddress(String address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
}
