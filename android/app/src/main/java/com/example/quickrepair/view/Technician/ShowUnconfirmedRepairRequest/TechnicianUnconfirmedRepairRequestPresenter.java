package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.R;
import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.util.Utilities;

public class TechnicianUnconfirmedRepairRequestPresenter {
    private TechnicianUnconfirmedRepairRequestView view;
    private RepairRequestDAO repairRequestDAOMemory;
    private RepairRequest repairRequest;

    TechnicianUnconfirmedRepairRequestPresenter(){}

    public void searchRepairRequestData(int repairRequestId){
        if(repairRequestId == 0){
            view.showError("Something went wrong");
        }

        this.repairRequest = repairRequestDAOMemory.find(repairRequestId);

        if(this.repairRequest == null){
            view.showError("Something went wrong");
        }
        view.setJob(repairRequest.getJob().getJobType().getName());
        view.setConsumerName("From: " + "\n" + repairRequest.getCustomer().getName());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));
        view.setButtonsListeners();
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
