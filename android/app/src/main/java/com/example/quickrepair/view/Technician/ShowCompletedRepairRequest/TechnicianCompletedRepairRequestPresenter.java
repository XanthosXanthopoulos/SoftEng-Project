package com.example.quickrepair.view.Technician.ShowCompletedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.Repair;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.util.Utilities;

public class TechnicianCompletedRepairRequestPresenter {

    private RepairRequestDAO repairRequestDAO;
    private TechnicianCompletedRepairRequestView view;
    private RepairRequest repairRequest;


    public void searchRepairRequestData(int repairRequestId){
        if(repairRequestId == 0){
            view.showError("Something went wrong");
            return;
        }

        this.repairRequest = repairRequestDAO.find(repairRequestId);

        if(this.repairRequest == null){
            view.showError("Something went wrong");
            return;
        }
        view.setJob(repairRequest.getJob().getJobType().getName());
        view.setConsumerName("From: " + "\n" + repairRequest.getCustomer().getUsername());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));
        view.setEstimatedDuration("Estimated Duration: " + "\n" + repairRequest.getEstimatedDuration());
        if(repairRequest.getRepair().getPayment() != null) {
            view.setCost("Cost: " + "\n" + repairRequest.getRepair().getPayment().getCost());
        }else{
            view.setCost("Cost: " + "\n" +" - ");
        }
    }
    public void setView(TechnicianCompletedRepairRequestView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }
    public void setRepairRequestDAO(RepairRequestDAOMemory repairRequestDAO) {
        this.repairRequestDAO = repairRequestDAO;
    }
}
