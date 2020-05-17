package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.R;
import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;

public class TechnicianUnconfirmedRepairRequestPresenter {
    private TechnicianUnconfirmedRepairRequestView view;
    private RepairRequestDAO repairRequestDAOMemory;
    private RepairRequest repairRequest;

    TechnicianUnconfirmedRepairRequestPresenter(){}

    public RepairRequest searchRepairRequestData(int repairRequestId){
        this.repairRequest = repairRequestDAOMemory.find(repairRequestId);
        return repairRequest;
    }

    public void setReject(){
        repairRequestDAOMemory.find(repairRequest.getUid()).reject();
        view.reject();
    }

    public void setConfirm(String estimatedDuration){
        try{
            int estimatedDurationInt = Integer.parseInt(estimatedDuration);
            repairRequestDAOMemory.find(repairRequest.getUid()).confirm(estimatedDurationInt);
            view.confirm();
        }catch (Exception e){
            view.showError("Please enter estimated duration(minutes)");
        }
    }

    public void setView(TechnicianUnconfirmedRepairRequestView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

    public void setRepairRequestDAOMemory(RepairRequestDAO repairRequestDAOMemory) {
        this.repairRequestDAOMemory = repairRequestDAOMemory;
    }
}
