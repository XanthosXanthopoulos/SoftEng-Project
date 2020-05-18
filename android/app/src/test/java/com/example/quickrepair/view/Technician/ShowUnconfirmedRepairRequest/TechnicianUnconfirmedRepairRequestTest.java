package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechnicianUnconfirmedRepairRequestTest {
    TechnicianUnconfirmedRepairRequestViewStub view;
    TechnicianUnconfirmedRepairRequestPresenter presenter;
    RepairRequestDAO repairRequestDAO;
    private static int repairRequestID;
    @Before
    public void setUp(){
        view = new TechnicianUnconfirmedRepairRequestViewStub();
        presenter = new TechnicianUnconfirmedRepairRequestPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        repairRequestDAO = new RepairRequestDAOMemory();
        presenter.setRepairRequestDAOMemory(repairRequestDAO);
        repairRequestID = 1;
        presenter.searchRepairRequestData(repairRequestID);
    }
    @Test
    public void checkSetters(){
        Assert.assertEquals(6, view.getSumOfsetter());
    }

    @Test
    public void testReject(){
        presenter.setReject();
        Assert.assertEquals(1, view.getState());
        Assert.assertEquals(RepairRequest.Status.REJECTED, repairRequestDAO.find(repairRequestID).getStatus());
    }
    @Test
    public void testConfirmOk(){
        presenter.setConfirm("1");
        Assert.assertEquals(2, view.getState());
        Assert.assertEquals(RepairRequest.Status.CONFIRMED, repairRequestDAO.find(repairRequestID).getStatus());
    }
    @Test
    public void testError(){
        presenter.setConfirm("Hello");
        Assert.assertEquals(3, view.getState());
    }

    @Test
    public void testSearchErrorZero(){
        presenter.searchRepairRequestData(0);
        Assert.assertEquals(3, view.getState());
    }

    @Test
    public void testSearchErrorNull(){
        presenter.searchRepairRequestData(100);
        Assert.assertEquals(3, view.getState());
    }

}
