package com.example.quickrepair.view.Customer.RepairRequests;

public interface CustomerRepairRequestsView {

    void returnRepairRequestUnconfirmed(int repairRequestUid);

    void returnRepairRequestConfirmed(int repairRequestUid);

    void returnRepairRequestCompleted(int repairRequestUid);

    void editData();
    void search();
    void showError(String message);
}
