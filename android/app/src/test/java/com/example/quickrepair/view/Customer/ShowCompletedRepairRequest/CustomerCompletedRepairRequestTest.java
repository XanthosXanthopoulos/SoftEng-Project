package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerCompletedRepairRequestTest {
    CustomerCompletedRepairRequestViewStub view;
    CustomerCompletedRepairRequestPresenter presenter;

    private static int repairRequestNotPayedID;
    private static int repairRequestPayedID;
    private RepairRequestDAO repairRequestDAO;

    @Before
    public void setUp(){
        view = new CustomerCompletedRepairRequestViewStub();
        presenter = new CustomerCompletedRepairRequestPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        repairRequestDAO = new RepairRequestDAOMemory();
        presenter.setRepairRequestDAO((RepairRequestDAOMemory) repairRequestDAO);
    }
    @Test
    public void checkSettersNotPayed(){
        repairRequestNotPayedID = 7;
        presenter.searchRepairRequestData(repairRequestNotPayedID);
        Assert.assertEquals(7, view.getSumOfsetter());
        Assert.assertEquals(3, view.getState());
    }

    @Test
    public void checkSettersPayed(){
        repairRequestPayedID = 5;
        presenter.searchRepairRequestData(repairRequestPayedID);
        Assert.assertEquals(7, view.getSumOfsetter());
        Assert.assertEquals(2, view.getState());
    }

    @Test
    public void checkSearchErrorZero(){
        presenter.searchRepairRequestData(0);
        Assert.assertEquals(1, view.getState());
    }
    @Test
    public void checkSearchError(){
        presenter.searchRepairRequestData(100);
        Assert.assertEquals(1, view.getState());
    }
}
