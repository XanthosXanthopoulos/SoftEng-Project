package com.example.quickrepair.view.Customer.RegisterCustomer;

import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;
import com.example.quickrepair.view.User.LoginUser.LoginPresenter;
import com.example.quickrepair.view.User.LoginUser.LoginViewStub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerRegisterTest
{
    CustomerRegisterPresenter presenter;
    CustomerRegisterViewStub view;

    @Before
    public void setup()
    {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new CustomerRegisterViewStub();
        presenter = new CustomerRegisterPresenter();
        presenter.setTechnicianDAO(new TechnicianDAOMemory());
        presenter.setCustomerDAO(new CustomerDAOMemory());
        presenter.setView(view);
    }

    @Test
    public void registerTest()
    {
        presenter.setCustomer(0);
        presenter.setUpDataSource();

        presenter.registerCustomer("John", "Doe", "1234567890", "example@example.com", "1234567890123456789012", "joe", "123");
        Assert.assertNull(view.getErrorTitle());
        Assert.assertNull(view.getErrorMessage());
        Assert.assertNotNull(presenter.customer);

        Assert.assertNotEquals(0, presenter.customer.getUid());
    }

    @Test
    public void alreadyExistTest()
    {
        presenter.setCustomer(0);
        presenter.setUpDataSource();

        presenter.registerCustomer("John", "Doe", "1234567890", "example@example.com", "1234567890123456789012", "ann", "123");
        Assert.assertEquals(view.getErrorTitle(), "Username already exist");
        Assert.assertEquals(view.getErrorMessage(), "This username is already in use by another user.");
        Assert.assertNull(presenter.customer);

        presenter.registerCustomer("John", "Doe", "1234567890", "example@example.com", "1234567890123456789012", "aggelidis", "123");
        Assert.assertEquals(view.getErrorTitle(), "Username already exist");
        Assert.assertEquals(view.getErrorMessage(), "This username is already in use by another user.");
        Assert.assertNull(presenter.customer);
    }

    @Test
    public void registerFailTest()
    {
        presenter.setCustomer(0);
        presenter.setUpDataSource();

        presenter.registerCustomer("", "Doe", "1234567890", "example@example.com", "1234567890123456789012", "joe", "123");
        Assert.assertEquals(view.getErrorTitle(), "Invalid value");
        Assert.assertNull(presenter.customer);
    }

    @Test
    public void updateTest()
    {
        presenter.setCustomer(1);
        presenter.setUpDataSource();

        Assert.assertNotNull(presenter.customer);
        Assert.assertEquals(presenter.customer.getName(), "ΑΝΝΑ");
        Assert.assertEquals(presenter.customer.getSurname(), "ΑΡΓΥΡΑΚΗ");
        Assert.assertEquals(presenter.customer.getPhoneNumber(), "0000000001");
        Assert.assertEquals(presenter.customer.getEmail(), "anna@repair.gr");
        Assert.assertEquals(presenter.customer.getBankAccount(), "0034560101011234567890");
        Assert.assertEquals(presenter.customer.getUsername(), "ann");
        Assert.assertEquals(presenter.customer.getPassword(), "123");
        Assert.assertEquals(presenter.customer.getUid(), 1);
    }
}
