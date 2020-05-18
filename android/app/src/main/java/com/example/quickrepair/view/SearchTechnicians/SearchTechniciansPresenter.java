package com.example.quickrepair.view.SearchTechnicians;

import com.example.quickrepair.dao.AreaDAO;
import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.SpecialtyDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.memorydao.MemoryInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Presenter class that implements the search technicians use case
 */
public class SearchTechniciansPresenter {
    private SearchTechniciansView view;
    private TechnicianDAO technicianDAO;
    private SpecialtyDAO specialtyDAO;
    private JobTypeDAO jobTypeDAO;
    private AreaDAO areaDAO;


    public SearchTechniciansPresenter(TechnicianDAO technicianDAO,
                                      SpecialtyDAO specialtyDAO , JobTypeDAO jobTypeDAO ,
                                      AreaDAO areaDAO
                                      ){
        this.technicianDAO = technicianDAO;
        this.specialtyDAO = specialtyDAO;
        this.jobTypeDAO = jobTypeDAO;
        this.areaDAO = areaDAO;
    }

    public void setView(SearchTechniciansView view){
        this.view = view;
    }

    int loggedInUser = -1;

    /**
     * Sets the user that is currently logged in
     * @param userId
     */
    public void setLoggedInUser(int userId){
        loggedInUser = userId;
    }
    /**
     * Starts the use case by looking through the dao and keeping all the specialties
     */
    public void onStart(){
        List<Specialty> specialties =  specialtyDAO.findAll();
        List<Integer> specialtyIds = new ArrayList<>();
        List<String> specialtyNames = new ArrayList<>();
        for(Specialty e : specialties){
            specialtyIds.add(e.getUid());
            specialtyNames.add(e.getName());
        }
        view.setSpecialtiesSource(specialtyIds , specialtyNames);
        view.setJobTypeSpinnerEnabled(false);
        view.setAreasSource(areaDAO.getAreas());
    }
    /**
     *  Responds to the user selecting a specialty
     */
    public void selectSpecialty(int specialtyId){
        Specialty selectedSpecialty =  specialtyDAO.find(specialtyId);
        List<JobType> jobTypes =  jobTypeDAO.findAll();
        List<Integer> jobTypeIds = new ArrayList<>();
        List<String> jobTypeNames = new ArrayList<>();
        //Selecting only jobtypes of selected specialty
        for(JobType e : jobTypes){
            if(e.getSpecialty().getUid() == specialtyId){
                jobTypeIds.add(e.getUid());
                jobTypeNames.add(e.getName());
            }
        }
        view.setJobTypesSource(jobTypeIds , jobTypeNames);
        //Setting the job type drop down as enabled so the user can choose
        view.setJobTypeSpinnerEnabled(true);
    }


    /**
     * A list that represents the technicians that are showed on the view
     */
    private int selectedJobTypeId;
    /**
     * Selects the jobType to search for
     * @param jobTypeId
     */
    public void selectJobType(int jobTypeId){
        selectedJobTypeId = jobTypeId;
        repopulateTechnicianList();

    }

    /**
     * Filters the technicians list according to the selected area
     * @param area the selected area
     */
    String selectedArea;
    public void filterArea(String area){
        selectedArea = area;
        //Refreshing the technicianList
        repopulateTechnicianList();

    }

    /**
     * Filters the technician list according to their maximum price for the jobtype
     * @param input the input maximum price
     */
    double selectedMaxPrice = 10000;
    public void filterByMaxPrice(String input){
        double price = 0;
        //Checking if user has entered a valid price
        try{
            price = Double.parseDouble(input);
        }
        catch (NumberFormatException e){
            view.showErrorMessage("Please enter a valid from of price");
            return;
        }
        selectedMaxPrice = price;
        List<Technician> tempTechnicians = new ArrayList<>();
        //Refreshing the technicianList
        repopulateTechnicianList();

    }

    public void onTechnicianClick(int technicianId){
        if(loggedInUser == -1){
            //User is not logged In
            view.showErrorMessage("You must be logged in to do that!");
            view.navigateToLogin();
        }
        view.navigateToRequestRepair(technicianId , selectedJobTypeId);
    }
    //TODO Select Date

    private void repopulateTechnicianList() {
        List<Integer> technicianIds = new ArrayList<>();
        //TODO Get average ratings from a technician reference for a jobtype or whatever
        List<Double> averageTechnicianRatings = new ArrayList<>();
        List<String> technicianNames = new ArrayList<>();
        List<Double> averageRatings = new ArrayList<>();
        //TODO Get price for a job type from a technician
        List<Double> prices = new ArrayList<>();
        for(Technician technician : technicianDAO.findAll()){
            boolean offersJobType = offersJobType(technician.getUid() , selectedJobTypeId);
            boolean underMaxPrice = offersJobTypeForLessThan(technician.getUid() , selectedJobTypeId , selectedMaxPrice);
            boolean servesArea = selectedArea == null ? true : technician.servesArea(selectedArea);
            if(!(offersJobType && underMaxPrice && servesArea)){
                continue;
            }
            technicianIds.add(technician.getUid());
            technicianNames.add(technician.getName());
            averageTechnicianRatings.add(0.0);
            prices.add(getTechnicianPriceForJobType(technician.getUid() , selectedJobTypeId));
        }
        view.populateTechnicianList(technicianIds , technicianNames , averageRatings , prices);
    }



    //TODO Move this in domain or dao ?
    private boolean offersJobType(int technicianId , int jobTypeId){
        Technician technician = technicianDAO.find(technicianId);
        for(Job job : technician.getJobs()){
            if(job.getJobType().getUid() == jobTypeId){
                return true;
            }
        }
        return false;
    }
    private boolean offersJobTypeForLessThan(int technicianId , int jobTypeId , double price){
        Technician technician = technicianDAO.find(technicianId);
        for(Job job : technician.getJobs()){
            if(job.getJobType().getUid() == jobTypeId){
                if(job.getPrice() <= price){
                    return true;
                }
            }
        }
        return false;
    }
    private double getTechnicianPriceForJobType(int technicianId , int jobTypeId){
        Technician technician = technicianDAO.find(technicianId);
        for(Job job : technician.getJobs()){
            if(job.getJobType().getUid() == jobTypeId){
                return job.getPrice();
            }
        }
        return 0;
    }


}
