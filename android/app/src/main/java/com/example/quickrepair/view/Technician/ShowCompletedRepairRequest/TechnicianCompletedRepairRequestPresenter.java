package com.example.quickrepair.view.Technician.ShowCompletedRepairRequest;

import com.example.quickrepair.domain.Repair;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.util.Utilities;

public class TechnicianCompletedRepairRequestPresenter {

    private RepairRequestDAOMemory repairRequestDAO;
    private TechnicianCompletedRepairRequestView view;
    private RepairRequest repairRequest;

    public void setRepairRequestDAO(RepairRequestDAOMemory repairRequestDAO) {
        this.repairRequestDAO = repairRequestDAO;
    }
    public void searchRepairRequestData(int repairRequestId){
        if(repairRequestId == 0){
            view.showError("Something went wrong");
        }

        this.repairRequest = repairRequestDAO.find(repairRequestId);

        if(this.repairRequest == null){
            view.showError("Something went wrong");
        }
        view.setJob(repairRequest.getJob().getJobType().getName());
        view.setConsumerName("From: " + "\n" + repairRequest.getCustomer().getName());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));
        view.setEstimatedDuration("Estimated Duration: " + "\n" +"");
        view.setPrice("");
    }
    public void setView(TechnicianCompletedRepairRequestView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }
}
