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

    @Test
    public void searchForConfirmedRepairRequests(){
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.CONFIRMED);
        Assert.assertEquals(2, repairRequests.size());
    }

    @Test
    public void searchForCompletedRepairRequests(){
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.COMPLETED);
        Assert.assertEquals(4, repairRequests.size());
    }

    @Test
    public void clickedParticularUnconfirmed(){
        view.returnRepairRequestUnconfirmed(1);
        Assert.assertEquals(1, view.getCurrentRepairRequestUid());
    }

    @Test
    public void clickedParticularConfirmed(){
        view.returnRepairRequestConfirmed(1);
        Assert.assertEquals(1, view.getCurrentRepairRequestUid());
    }

    @Test
    public void clickedParticularCompleted(){
        view.returnRepairRequestConfirmed(1);
        Assert.assertEquals(1, view.getCurrentRepairRequestUid());
    }

    @Test
    public void clickedEditPage(){
        view.editData();
        Assert.assertEquals(true, view.getPressEdit());
    }
}
