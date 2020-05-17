package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechnicianUnconfirmedRepairRequestTest {
    TechnicianUnconfirmedRepairRequestViewStub view;
    TechnicianUnconfirmedRepairRequestPresenter presenter;
    RepairRequest repairRequest;

    @Before
    public void setUp(){
        view = new TechnicianUnconfirmedRepairRequestViewStub();
        presenter = new TechnicianUnconfirmedRepairRequestPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        presenter.setRepairRequestDAOMemory(new RepairRequestDAOMemory());
        repairRequest = presenter.searchRepairRequestData(1);
    }
    @Test
    public void testSearch(){
        Assert.assertNotNull(repairRequest);
    }
    @Test
    public void testReject(){
        presenter.setReject();
        Assert.assertEquals(RepairRequest.Status.REJECTED, repairRequest.getStatus());
        Assert.assertEquals(1, view.getState());
    }
    @Test
    public void testConfirmOk(){
        presenter.setConfirm("1");
        Assert.assertEquals(RepairRequest.Status.CONFIRMED, repairRequest.getStatus());
        Assert.assertEquals(2, view.getState());
    }
    @Test
    public void testError(){
        presenter.setConfirm("Hello");
        Assert.assertEquals(RepairRequest.Status.UNCONFIRMED, repairRequest.getStatus());
        Assert.assertEquals(3, view.getState());
    }
}
