package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

public interface CustomerCompletedRepairRequestView {
    void showError(String message);

    void setJob(String job);
    void setTechnicianName(String technicianName);
    void setAddress(String address);
    void setComments(String comments);
    void setConductionDate(String conductionDate);
    void setEstimatedDuration(String estimatedDuration);
    void setCost(String cost);
    void setEvaluationData(String title, String comments, String rate);

    void setPayAndEvaluationFields();
    void setPayListener();


    void donePayAndEvaluate();
}
