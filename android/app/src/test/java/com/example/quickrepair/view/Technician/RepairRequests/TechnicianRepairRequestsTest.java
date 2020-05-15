package com.example.quickrepair.view.Technician.RepairRequests;

import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TechnicianRepairRequestsTest {
    TechnicianRepairRequestsViewStub view;
    TechnicianRepairRequestsPresenter presenter;

    @Before
    public void setUp(){
        view = new TechnicianRepairRequestsViewStub();
        presenter = new TechnicianRepairRequestsPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        presenter.setRepairRequestDAO(new RepairRequestDAOMemory());
    }

    @Test
    public void searchForUnconfirmedRepairRequests(){
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.UNCONFIRMED);
        Assert.assertEquals(2, repairRequests.size());
    }
}
