package com.example.quickrepair.view.Customer.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.util.Utilities;

public class CustomerUnconfirmedRepairRequestPresenter {
    private CustomerUnconfirmedRepairRequestView view;
    private RepairRequestDAO repairRequestDAO;
    private RepairRequest repairRequest;

    CustomerUnconfirmedRepairRequestPresenter(){}

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
        view.setTechnicianName("To: " + "\n" + repairRequest.getJob().getTechnician().getUsername());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));

    }

    public void setRepairRequestDAO(RepairRequestDAOMemory repairRequestDAOMemory) {
        this.repairRequestDAO = repairRequestDAOMemory;
    }

    public void clearView() {
        this.view = null;
    }

    public void setView(CustomerUnconfirmedRepairRequestActivity view) {
        this.view = view;
    }

}
