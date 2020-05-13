package com.example.quickrepair.view;

import com.example.quickrepair.view.HomePage.HomePagePresenter;
import com.example.quickrepair.view.HomePage.HomePageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomePagePresenterTest {
    HomePageViewStub view;
    HomePagePresenter presenter;

    @Before
    public void setUp(){
        view = new HomePageViewStub();
        presenter = new HomePagePresenter();
        presenter.setView(view);
    }

    @Test
    public void testLogIn(){
        view.login();
        Assert.assertEquals(1, view.getClickedButton());
    }

    @Test
    public void testRegisterTechnician(){
        view.registerAsTechnician();
        Assert.assertEquals(2, view.getClickedButton());
    }

    @Test
    public void testRegisterCustomer(){
        view.registerAsCustomer();
        Assert.assertEquals(3, view.getClickedButton());
    }

    @Test
    public void testSearch(){
        view.searchTechnicians();
        Assert.assertEquals(4, view.getClickedButton());
    }
}
