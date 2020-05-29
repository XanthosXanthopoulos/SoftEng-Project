package com.example.quickrepair.view.SearchTechnicians;

import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.memorydao.MemoryInitializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTechnicianTest
{
    SearchTechnicianViewStub view;
    SearchTechniciansPresenter presenter;

    @Before
    public void setup()
    {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new SearchTechnicianViewStub();
        presenter = new SearchTechniciansPresenter(initializer.getTechnicianDAO(), initializer.getSpecialtyDAO(), initializer.getJobTypeDAO(), initializer.getAreaDAO());
        presenter.setView(view);
    }

    @Test
    public void setSpecialityTest()
    {
        presenter.onStart();
        presenter.selectSpecialty(1);

        Assert.assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), view.getJobTypeIds());
    }

    @Test
    public void searchTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "", "Argos", "1", "1", "1");
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1, 2)), view.getTechnicianIds());
    }

    @Test
    public void searchLessPriceTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "13", "Argos", "1", "1", "1");
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1)), view.getTechnicianIds());
    }

    @Test
    public void searchEmptyTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "1", "Argos", "1", "1", "1");
        Assert.assertEquals(new ArrayList<>(), view.getTechnicianIds());
    }

    @Test
    public void searchFailTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "1", "Argos", "", "1", "1");
        Assert.assertEquals("Please enter a valid Date (YYYY/MM/DD)", view.getError());
    }

    @Test
    public void searchFailInvalidDateTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "1", "Argos", "1", "1", "40");
        Assert.assertEquals("Please enter a valid Date (YYYY/MM/DD)", view.getError());
    }

    @Test
    public void searchFailPriceTest()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "a", "Argos", "1", "1", "1");
        Assert.assertEquals("Please enter a valid from of price", view.getError());
    }

    @Test
    public void selectLogin()
    {
        presenter.setLoggedInUser(1);
        presenter.onStart();

        presenter.search(1, "", "Argos", "1", "1", "1");
        presenter.onTechnicianClick(1);
        Assert.assertEquals(1, view.getTechnicianId());
        Assert.assertEquals(1, view.getJobTypeId());
        Assert.assertEquals(1, view.getYear());
        Assert.assertEquals(1, view.getMonth());
        Assert.assertEquals(1, view.getDayOfMonth());
        Assert.assertEquals(false, view.isToLogin());
    }

    @Test
    public void selectNoLogin()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "", "Argos", "1", "1", "1");
        presenter.onTechnicianClick(1);
        Assert.assertEquals(1, view.getTechnicianId());
        Assert.assertEquals(1, view.getJobTypeId());
        Assert.assertEquals(1, view.getYear());
        Assert.assertEquals(1, view.getMonth());
        Assert.assertEquals(1, view.getDayOfMonth());
        Assert.assertEquals(true, view.isToLogin());
    }

    @Test
    public void selectNoDateLogin()
    {
        presenter.setLoggedInUser(0);
        presenter.onStart();

        presenter.search(1, "", "Argos", "", "1", "1");
        presenter.onTechnicianClick(1);

        Assert.assertEquals("Please enter a valid Date (YYYY/MM/DD)", view.getError());
    }
}
