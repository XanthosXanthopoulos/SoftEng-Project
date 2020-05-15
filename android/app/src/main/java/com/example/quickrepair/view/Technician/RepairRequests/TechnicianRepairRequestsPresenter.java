package com.example.quickrepair.view.Technician.RepairRequests;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.view.HomePage.HomePageView;

import java.util.ArrayList;


public class TechnicianRepairRequestsPresenter {
    private TechnicianRepairRequestsView view;
    private RepairRequestDAOMemory repairRequestDAO;

    public TechnicianRepairRequestsPresenter(){}

    public ArrayList<RepairRequest> searchRepairRequests(int techid, RepairRequest.Status status){
        if(techid != 0 && status!=null){
            //get repair requests from DAO
            return repairRequestDAO.findAllForTechnicianByStatus(techid, status);
        }
        return null;
    }

    public void onRepairRequestSelectedUnconfirmed(RepairRequest repairRequest){
        view.returnRepairRequestUnconfirmed(repairRequest.getUid());
    }

    public void onRepairRequestSelectedConfirmed(RepairRequest repairRequest){
        view.returnRepairRequestConfirmed(repairRequest.getUid());
    }

    public void onRepairRequestSelectedCompleted(RepairRequest repairRequest){
        view.returnRepairRequestCompleted(repairRequest.getUid());
    }

    public void setView(TechnicianRepairRequestsView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }
}
