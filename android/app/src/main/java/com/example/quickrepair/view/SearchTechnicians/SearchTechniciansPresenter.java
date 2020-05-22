package com.example.quickrepair.view.SearchTechnicians;

import android.util.Log;

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
    int selectedSpecialtyId = -1;
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
        selectedSpecialtyId = specialtyId;
        view.setJobTypesSource(jobTypeIds , jobTypeNames);
        //Setting the job type drop down as enabled so the user can choose
        view.setJobTypeSpinnerEnabled(true);
    }
    int year = -1;
    int month = -1;
    int dayOfMonth = -1;

    /**
     * Sets the date the customer wants the technician to be available
     */
    public void setDate(String yearString, String monthString, String dayOfMonthString){
        try {
            int year = Integer.parseInt(yearString);
            int month = Integer.parseInt(monthString);
            int dayOfMonth = Integer.parseInt(dayOfMonthString);
            if (year >= 0 && month <= 12 && month >= 1 && dayOfMonth <= 31 && dayOfMonth >= 0) {
                this.year = year;
                this.month = month;
                this.dayOfMonth = dayOfMonth;
            } else {
                throw new Exception("");
            }
        }
        catch (Exception e){
            view.showErrorMessage("Please enter a valid Date (YYYY/MM/DD)");
        }
    }
    /**
     * A list that represents the technicians that are showed on the view
     */
    private int selectedJobTypeId = -1;
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
    String selectedArea = null;
    public void setArea(String area){
        selectedArea = area;
        //Refreshing the technicianList
        repopulateTechnicianList();

    }

    /**
     * Filters the technician list according to their maximum price for the jobtype
     * @param input the input maximum price
     */
    double selectedMaxPrice = -1;
    public void setMaxPrice(String input){
        //If the input is null or empty we don't set an upper bound
        if(input == null || input.equals("")) return;
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
        //Refreshing the technicianList
        repopulateTechnicianList();

    }

    /**
     * Called when a user clicks on a technician on the list
     */
    public void onTechnicianClick(int technicianId){
        if(year == -1 || month == -1 || dayOfMonth == -1){
            view.showErrorMessage("Please enter a valid Date (YYYY/MM/DD)");
            return;
        }
        if(loggedInUser == -1){
            //User is not logged In
            view.showErrorMessage("You must be logged in to do that!");
            view.navigateToLogin();
            return;
        }
        view.navigateToRequestRepair(technicianId , selectedJobTypeId , year , month , dayOfMonth);
    }
    //TODO Select Date

    /**
     * Searches the DAO and applies the given criteria to find the right technicians and populates
     * the view with results
     */
    private void repopulateTechnicianList() {
        System.out.println("Selected specialty id " + selectedSpecialtyId);
        System.out.println("Selected jt id " + selectedJobTypeId);
        System.out.println("Selected mp id " + selectedMaxPrice);
        System.out.println("Selected area id " + selectedArea);
        List<Integer> technicianIds = new ArrayList<>();
        //TODO Get average ratings from a technician reference for a jobtype or whatever
        List<String> technicianNames = new ArrayList<>();
        List<Double> averageRatings = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        for(Technician technician : technicianDAO.findAll()){
            boolean hasSpecialty = selectedSpecialtyId==-1 || technician.getSpecialty().getUid() == selectedSpecialtyId;
            boolean offersJobType = selectedJobTypeId == -1 || offersJobType(technician.getUid() , selectedJobTypeId);
            boolean underMaxPrice = selectedJobTypeId == -1 || selectedMaxPrice < 0 || offersJobTypeForLessThan(technician.getUid() , selectedJobTypeId , selectedMaxPrice);
            boolean servesArea = selectedArea == null || technician.servesArea(selectedArea);
            if(!(hasSpecialty && offersJobType && underMaxPrice && servesArea)){
                continue;
            }
            technicianIds.add(technician.getUid());
            technicianNames.add(technician.getName());
            averageRatings.add(0.0);
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
