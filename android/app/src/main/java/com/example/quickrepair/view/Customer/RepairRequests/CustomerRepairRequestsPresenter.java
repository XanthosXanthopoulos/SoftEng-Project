package com.example.quickrepair.view.Customer.RepairRequests;

import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import java.util.ArrayList;

public class CustomerRepairRequestsPresenter
{
    private CustomerRepairRequestsView view;
    private RepairRequestDAOMemory repairRequestDAO;
    private ArrayList<RepairRequest> repairRequests;

    public CustomerRepairRequestsPresenter()
    {
    }

    public ArrayList<RepairRequest> searchRepairRequests(int customerID, RepairRequest.Status status)
    {
        if (customerID != 0 && status != null)
        {
            //get repair requests from DAO
            repairRequests = this.repairRequestDAO.findAllForCustomerByStatus(customerID, status);
            return repairRequests;
        }

        return null;
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

    public void onEditDataPage()
    {
        view.editData();
    }

    public void searchForJob()
    {
        view.search();
    }

    public void setRepairRequestDAO(RepairRequestDAOMemory repairRequestDAO)
    {
        this.repairRequestDAO = repairRequestDAO;
    }

    public void setView(CustomerRepairRequestsView view)
    {
        this.view = view;
    }

    public void clearView()
    {
        this.view = null;
    }
}
