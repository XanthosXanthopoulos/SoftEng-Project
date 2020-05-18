package com.example.quickrepair.view.Technician.ShowConfirmedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechnicianConfirmedRepairRequestTest {

    TechnicianConfirmedRepairRequestViewStub view;
    TechnicianConfirmedRepairRequestPresenter presenter;
    private static int repairRequestID;
    private RepairRequestDAO repairRequestDAO;
    @Before
    public void setUp(){
        view = new TechnicianConfirmedRepairRequestViewStub();
        presenter = new TechnicianConfirmedRepairRequestPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        repairRequestDAO = new RepairRequestDAOMemory();
        presenter.setRepairRequestDAOMemory((RepairRequestDAOMemory) repairRequestDAO);
        repairRequestID = 3;
        presenter.searchRepairRequestData(repairRequestID);
    }
    @Test
    public void checkSetters(){
        Assert.assertEquals(7, view.getSumOfsetter());
    }

    @Test
    public void testError(){
        presenter.setCompleted("Hello");
        Assert.assertEquals(2, view.getState());
    }

    @Test
    public void testCompleted(){
        presenter.setCompleted("2");
        Assert.assertEquals(1, view.getState());
        Assert.assertEquals(RepairRequest.Status.COMPLETED, repairRequestDAO.find(repairRequestID).getStatus());
    }
    @Test
    public void testSearchErrorZero(){
        presenter.searchRepairRequestData(0);
        Assert.assertEquals(2, view.getState());
    }

    @Test
    public void testSearchErrorNull(){
        presenter.searchRepairRequestData(100);
        Assert.assertEquals(2, view.getState());
    }
}
