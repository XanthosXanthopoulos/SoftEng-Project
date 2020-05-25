package com.example.quickrepair.view.Customer.RepairRequests;

import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CustomerRepairRequestsTest
{
    CustomerRepairRequestsViewStub view;
    CustomerRepairRequestsPresenter presenter;
    RepairRequestDAOMemory repairRequestDAOMemory;

    @Before
    public void setUp()
    {
        view = new CustomerRepairRequestsViewStub();
        presenter = new CustomerRepairRequestsPresenter();
        presenter.setView(view);
        new MemoryInitializer().prepareData();
        repairRequestDAOMemory = new RepairRequestDAOMemory();
        presenter.setRepairRequestDAO(repairRequestDAOMemory);
    }

    @Test
    public void searchForUnconfirmedRepairRequests()
    {
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.UNCONFIRMED);
        Assert.assertEquals(2, repairRequests.size());
    }

    @Test
    public void searchForConfirmedRepairRequests()
    {
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.CONFIRMED);
        Assert.assertEquals(1, repairRequests.size());
    }

    @Test
    public void searchForCompletedRepairRequests()
    {
        ArrayList<RepairRequest> repairRequests = presenter.searchRepairRequests(1, RepairRequest.Status.COMPLETED);
        Assert.assertEquals(2, repairRequests.size());
    }

    @Test
    public void clickedParticularUnconfirmed()
    {
        RepairRequest repairRequest = repairRequestDAOMemory.findAllForCustomerByStatus(1, RepairRequest.Status.UNCONFIRMED).get(0);
        presenter.onRepairRequestSelectedUnconfirmed(repairRequest);
        Assert.assertEquals(1, view.getCurrentRepairRequestUid());
        Assert.assertEquals(1, view.getCurrentState());
    }

    @Test
    public void clickedParticularConfirmed()
    {
        RepairRequest repairRequest = repairRequestDAOMemory.findAllForCustomerByStatus(1, RepairRequest.Status.CONFIRMED).get(0);
        presenter.onRepairRequestSelectedConfirmed(repairRequest);
        Assert.assertEquals(3, view.getCurrentRepairRequestUid());
        Assert.assertEquals(2, view.getCurrentState());
    }

    @Test
    public void clickedParticularCompleted()
    {
        RepairRequest repairRequest = repairRequestDAOMemory.findAllForCustomerByStatus(1, RepairRequest.Status.COMPLETED).get(0);
        presenter.onRepairRequestSelectedCompleted(repairRequest);
        Assert.assertEquals(5, view.getCurrentRepairRequestUid());
        Assert.assertEquals(3, view.getCurrentState());
    }

    @Test
    public void clickedEditPage()
    {
        view.editData();
        Assert.assertTrue(view.getPressEdit());
    }

    @Test
    public void clickedSearch()
    {
        view.search();
        Assert.assertTrue(view.isPressSearch());
    }

    @Test
    public void testError1()
    {
        presenter.onRepairRequestSelectedUnconfirmed(null);
        Assert.assertTrue(view.isError());
    }

    @Test
    public void testError2()
    {
        presenter.onRepairRequestSelectedConfirmed(null);
        Assert.assertTrue(view.isError());
    }

    @Test
    public void testError3()
    {
        presenter.onRepairRequestSelectedCompleted(null);
        Assert.assertTrue(view.isError());
    }
}
