package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

public interface CustomerCompletedRepairRequestView {
    void showError(String message);

    void setJob(String job);
    void setTechnicianName(String technicianName);
    void setAddress(String Address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setEstimatedDuration(String estimatedDuration);
    void setCost(String cost);

    void canNotPay();
    void setPayListener();

    void setNullCost();

    void donePayment();
}
