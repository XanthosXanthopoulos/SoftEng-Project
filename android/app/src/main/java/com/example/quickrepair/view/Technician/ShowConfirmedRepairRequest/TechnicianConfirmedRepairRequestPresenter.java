package com.example.quickrepair.view.Technician.ShowConfirmedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.util.Utilities;
import com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest.TechnicianUnconfirmedRepairRequestView;

public class TechnicianConfirmedRepairRequestPresenter {
    private TechnicianConfirmedRepairRequestView view;
    private RepairRequestDAO repairRequestDAO;
    private RepairRequest repairRequest;

    TechnicianConfirmedRepairRequestPresenter(){}

    public void searchRepairRequestData(int repairRequestId){
        if(repairRequestId == 0){
            view.showError("Something went wrong");
        }

        this.repairRequest = repairRequestDAO.find(repairRequestId);

        if(this.repairRequest == null){
            view.showError("Something went wrong");
        }
        view.setJob(repairRequest.getJob().getJobType().getName());
        view.setConsumerName("From: " + "\n" + repairRequest.getCustomer().getUsername());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));
        view.setButtonListeners();
    }

    public void setCompleted(String quantity) {
        try{
            int quantityInt = Integer.parseInt(quantity);
            repairRequestDAO.find(repairRequest.getUid()).complete(quantityInt);
            view.complete();
        }catch (Exception e){
            view.showError("Please enter estimated duration(minutes)");
        }

    }
    public void setView(TechnicianConfirmedRepairRequestView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

    public void setRepairRequestDAOMemory(RepairRequestDAOMemory repairRequestDAO) {
        this.repairRequestDAO = repairRequestDAO;
    }

}
