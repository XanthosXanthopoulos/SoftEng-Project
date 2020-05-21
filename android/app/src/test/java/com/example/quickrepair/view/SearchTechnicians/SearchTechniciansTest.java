package com.example.quickrepair.view.SearchTechnicians;

import com.example.quickrepair.dao.AreaDAO;
import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.SpecialtyDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchTechniciansTest {
    SearchTechniciansPresenter presenter;
    SearchTechniciansViewStub stub;
    AreaDAO areaDAO;
    TechnicianDAO technicianDAO;
    SpecialtyDAO specialtyDAO;
    JobTypeDAO jobTypeDAO;

    @Before
    public void setUp(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        areaDAO =  initializer.getAreaDAO();
        technicianDAO =  initializer.getTechnicianDAO();
        specialtyDAO =  initializer.getSpecialtyDAO();
        jobTypeDAO =  initializer.getJobTypeDAO();
        presenter = new SearchTechniciansPresenter(technicianDAO , specialtyDAO , jobTypeDAO , areaDAO);
        stub = new SearchTechniciansViewStub();
        stub.setPresenter(presenter);
        presenter.setView(stub);
        stub.onStart();
    }
    @Test
    public void normalExecution(){
        stub.setLoggedInUser(1);
        stub.chooseSpecialty(0);
        //Assert that the correct specialty was chosen
        Assert.assertTrue(specialtyDAO.findAll().get(0).getUid() == stub.specialtyIds.get(0));
        int selectedSpecialtyId = stub.specialtyIds.get(0);
        //Assert that every jobtype that resulted from choosing the specialty belongs to the selected
        //specialty
        for(Integer jobTypeId : stub.jobTypeIds){
            Assert.assertEquals(jobTypeDAO.find(jobTypeId).getSpecialty().getUid() , selectedSpecialtyId );
        }
        stub.chooseJobType(0);
        stub.setPrice("13");
        stub.setArea(2);
        stub.setDate("2020" , "5" , "5");

        String correctArea = areaDAO.getAreas().get(2);
        String chosenArea = stub.areas.get(2);
        Assert.assertEquals(correctArea , chosenArea);

        List<Integer> resultIds = stub.technicianIds;
        List<Technician> outputTechnicians = new ArrayList<>();
        for(Integer id : resultIds){
            outputTechnicians.add(technicianDAO.find(id));
        }
        //Checking that every technician offers the chosen Area
        for(Technician technician : outputTechnicians){
            Assert.assertTrue(technician.servesArea(chosenArea));
        }

        List<Double> resultPrices = stub.prices;
        //Checking that every output technician offers the selected jobType for the maximum price
        for(double price : resultPrices){
            Assert.assertTrue(price <= 13);
        }
        stub.chooseTechnician(0);
        Assert.assertTrue(stub.navDay == 5);
        Assert.assertTrue(stub.navMonth == 5);
        Assert.assertTrue(stub.navYear == 2020);
    }
    @Test
    public void setWrongPriceShowsErrorMessage(){
        stub.chooseSpecialty(0);
        stub.chooseJobType(0);
        stub.setPrice("wrongPrice");
        Assert.assertNotNull(stub.lastMessage);
    }
    @Test
    public void moveToRequestRepairWithoutLoggingIn(){
        stub.chooseSpecialty(0);
        stub.chooseJobType(0);
        stub.setDate("2020" , "10" , "1");
        stub.setPrice("500");
        stub.chooseTechnician(0);
        Assert.assertTrue(stub.lastMessage != null);
        Assert.assertTrue(stub.navigatedToLogin);
    }
    @Test
    public void setWrongFormOfDate(){
        stub.chooseSpecialty(0);
        stub.chooseJobType(0);
        stub.setDate("2020" , "13" , "1");
        stub.setPrice("500");
        stub.chooseTechnician(0);
        Assert.assertTrue(stub.lastMessage != null);
    }

    @Test
    public void noUpperBoundOnPrice(){
        stub.chooseSpecialty(0);
        stub.chooseJobType(0);
        stub.setDate("2020" , "11" , "1");
        stub.setArea(0);
        Assert.assertFalse(stub.technicianIds.isEmpty());
    }
}
