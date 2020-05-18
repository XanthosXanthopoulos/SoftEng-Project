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

    @Before
    public void setUp(){
        view = new TechnicianUnconfirmedRepairRequestViewStub();
        presenter = new TechnicianUnconfirmedRepairRequestPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        presenter.setRepairRequestDAOMemory(new RepairRequestDAOMemory());
        presenter.searchRepairRequestData(1);
    }
    @Test
    public void checkSetters(){
        Assert.assertEquals(6, view.getSumOfsetter());
    }

    @Test
    public void testReject(){
        presenter.setReject();
        Assert.assertEquals(1, view.getState());
    }
    @Test
    public void testConfirmOk(){
        presenter.setConfirm("1");
        Assert.assertEquals(2, view.getState());
    }
    @Test
    public void testError(){
        presenter.setConfirm("Hello");
        Assert.assertEquals(3, view.getState());
    }

}
