package com.example.quickrepair.view.Technician.RepairRequests;

import com.example.quickrepair.domain.RepairRequest;

import java.util.ArrayList;

public interface TechnicianRepairRequestsView {

    void returnRepairRequestUnconfirmed(int repairRequestUid);

    void returnRepairRequestConfirmed(int repairRequestUid);

    void returnRepairRequestCompleted(int repairRequestUid);

    void editData();
}
