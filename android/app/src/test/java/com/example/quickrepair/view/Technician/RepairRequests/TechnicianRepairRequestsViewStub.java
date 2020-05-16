package com.example.quickrepair.view.Technician.RepairRequests;

public class TechnicianRepairRequestsViewStub implements TechnicianRepairRequestsView{

    public static int currentRepairRequestUid = 0;

    @Override
    public void returnRepairRequestUnconfirmed(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
    }

    @Override
    public void returnRepairRequestConfirmed(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
    }

    @Override
    public void returnRepairRequestCompleted(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
    }

    public static int getCurrentRepairRequestUid() {
        return currentRepairRequestUid;
    }
}
