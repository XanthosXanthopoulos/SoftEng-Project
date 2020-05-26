package com.example.quickrepair.view.Technician.RepairRequests;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import java.util.ArrayList;


public class TechnicianRepairRequestsPresenter
{
    private TechnicianRepairRequestsView view;
    private RepairRequestDAO repairRequestDAO;
    private ArrayList<RepairRequest> repairRequests;

    public TechnicianRepairRequestsPresenter()
    {
    }

    public ArrayList<RepairRequest> searchRepairRequests(int technicianID, RepairRequest.Status status)
    {
        if (technicianID != 0 && status != null)
        {
            //get repair requests from DAO
            repairRequests = this.repairRequestDAO.findAllForTechnicianByStatus(technicianID, status);
            return repairRequests;
        }

        return null;
    }

    public void onRepairRequestSelectedUnconfirmed(RepairRequest repairRequest)
    {
        if (repairRequest == null || repairRequest.getUid() == 0)
        {
            view.showError("Something went wrong");
        }
        else
        {
            view.returnRepairRequestUnconfirmed(repairRequest.getUid());
        }
    }

    public void onRepairRequestSelectedConfirmed(RepairRequest repairRequest)
    {
        if (repairRequest == null || repairRequest.getUid() == 0)
        {
            view.showError("Something went wrong");
        }
        else
        {
            view.returnRepairRequestConfirmed(repairRequest.getUid());
        }
    }

    public void onRepairRequestSelectedCompleted(RepairRequest repairRequest)
    {
        if (repairRequest == null || repairRequest.getUid() == 0)
        {
            view.showError("Something went wrong");
        }
        else
        {
            view.returnRepairRequestCompleted(repairRequest.getUid());
        }
    }

    public void setRepairRequestDAO(RepairRequestDAO repairRequestDAO)
    {
        this.repairRequestDAO = repairRequestDAO;
    }

    public void onEditDataPage()
    {
        view.editData();
    }

    public void setView(TechnicianRepairRequestsView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        this.view = null;
    }

}
