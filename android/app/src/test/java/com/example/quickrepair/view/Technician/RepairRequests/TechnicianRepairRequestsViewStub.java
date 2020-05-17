package com.example.quickrepair.view.Technician.RepairRequests;

public class TechnicianRepairRequestsViewStub implements TechnicianRepairRequestsView{

    public static int currentRepairRequestUid = 0;
    public static boolean pressEdit = false;
    @Override
    public void returnRepairRequestUnconfirmed(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
        pressEdit = false;
    }

    @Override
    public void returnRepairRequestConfirmed(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
        pressEdit = false;
    }

    @Override
    public void returnRepairRequestCompleted(int repairRequestUid) {
        currentRepairRequestUid = repairRequestUid;
        pressEdit = false;
    }

    @Override
    public void editData() {
        pressEdit = true;
    }

    public static int getCurrentRepairRequestUid() {
        return currentRepairRequestUid;
    }

    public static boolean getPressEdit() {
        return pressEdit;
    }
}
