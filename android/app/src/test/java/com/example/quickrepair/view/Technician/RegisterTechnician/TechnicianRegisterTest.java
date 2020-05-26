package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.SpecialtyDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechnicianRegisterTest
{
    TechnicianRegisterPresenter presenter;
    TechnicianRegisterViewStub view;

    @Before
    public void setup()
    {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new TechnicianRegisterViewStub();
        presenter = new TechnicianRegisterPresenter();
        presenter.setTechnicianDAO(new TechnicianDAOMemory());
        presenter.setCustomerDAO(new CustomerDAOMemory());
        presenter.setSpecialtyDAO(new SpecialtyDAOMemory());
        presenter.setView(view);
    }

    @Test
    public void registerTest()
    {
        presenter.setTechnician(0);
        presenter.setUpDataSource();

        presenter.registerTechnician("John", "Doe", "1234567890", "example@example.com", "1234", "1234567890123456789012", "joe", "123", 1);
        Assert.assertNull(view.getErrorTitle());
        Assert.assertNull(view.getErrorMessage());
        Assert.assertNotNull(presenter.technician);

        Assert.assertNotEquals(0, presenter.technician.getUid());
    }

    @Test
    public void alreadyExistTest()
    {
        presenter.setTechnician(0);
        presenter.setUpDataSource();

        presenter.registerTechnician("John", "Doe", "1234567890", "example@example.com", "1234", "1234567890123456789012", "ann", "123", 1);
        Assert.assertEquals(view.getErrorTitle(), "Username already exist");
        Assert.assertEquals(view.getErrorMessage(), "This username is already in use by another user.");
        Assert.assertNull(presenter.technician);

        presenter.registerTechnician("John", "Doe", "1234567890", "example@example.com", "1234", "1234567890123456789012", "aggelidis", "123", 1);
        Assert.assertEquals(view.getErrorTitle(), "Username already exist");
        Assert.assertEquals(view.getErrorMessage(), "This username is already in use by another user.");
        Assert.assertNull(presenter.technician);

        presenter.registerTechnician("John", "Doe", "1234567890", "example@example.com", "2122772", "1234567890123456789012", "joe", "123", 1);
        Assert.assertEquals(view.getErrorTitle(), "Duplicate AFM");
        Assert.assertEquals(view.getErrorMessage(), "Please make sure you do not have already an account and you have typed the correct AFM.");
        Assert.assertNull(presenter.technician);
    }

    @Test
    public void registerFailTest()
    {
        presenter.setTechnician(0);
        presenter.setUpDataSource();

        presenter.registerTechnician("", "Doe", "1234567890", "example@example.com", "1234", "1234567890123456789012", "joe", "123", 1);
        Assert.assertEquals(view.getErrorTitle(), "Invalid value");
        Assert.assertNull(presenter.technician);

        presenter.registerTechnician("John", "Doe", "1234567890", "example@example.com", "1234", "1234567890123456789012", "joe", "123", 0);
        Assert.assertEquals(view.getErrorTitle(), "No speciality selected");
        Assert.assertEquals(view.getErrorMessage(), "You must choose a speciality.");
        Assert.assertNull(presenter.technician);
    }

    @Test
    public void updateTest()
    {
        presenter.setTechnician(1);
        presenter.setUpDataSource();

        Assert.assertNotNull(presenter.technician);
        Assert.assertEquals(presenter.technician.getName(), "ΓΙΑΝΝΗΣ");
        Assert.assertEquals(presenter.technician.getSurname(), "ΑΓΓΕΛΪΔΗΣ");
        Assert.assertEquals(presenter.technician.getPhoneNumber(), "1010101010");
        Assert.assertEquals(presenter.technician.getEmail(), "aggelidisgiannis@repair.gr");
        Assert.assertEquals(presenter.technician.getBankAccount(), "0034560101011234567890");
        Assert.assertEquals(presenter.technician.getAFM(), "2122772");
        Assert.assertEquals(presenter.technician.getUsername(), "aggelidis");
        Assert.assertEquals(presenter.technician.getPassword(), "123");
        Assert.assertEquals(presenter.technician.getSpecialty().getUid(), 1);
        Assert.assertEquals(presenter.technician.getUid(), 1);
    }
}
