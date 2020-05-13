package com.example.quickrepair.view.SearchTechnicians;

import com.example.quickrepair.dao.AreaDAO;
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


    public SearchTechniciansPresenter(SearchTechniciansView view , TechnicianDAO technicianDAO,
                                      SpecialtyDAO specialtyDAO , JobTypeDAO jobTypeDAO ,
                                      AreaDAO areaDAO
                                      ){
        this.technicianDAO = technicianDAO;
        this.view = view;
        this.specialtyDAO = specialtyDAO;
        this.jobTypeDAO = jobTypeDAO;
        this.areaDAO = areaDAO;
    }

    /**
     * Starts the use case by looking through the dao and showing the specialty dialog
     */
    public void onStart(){
        List<Specialty> specialties =  specialtyDAO.findAll();
        List<Integer> specialtiesIds = new ArrayList<>();
        List<String> specialtyNames = new ArrayList<>();
        for(Specialty e : specialties){
            specialtiesIds.add(e.getUid());
            specialtyNames.add(e.getName());
        }
        view.showSpecialtyDialog(specialtiesIds , specialtyNames);
    }

    /**
     * Selects the specialty and shows the dialog to choose the job tpye
     * @param specialtyId the id of the specialty to select
     */
    public void selectSpecialty(int specialtyId){
        Specialty specialty = specialtyDAO.find(specialtyId);
        Set<JobType> jobTypes = specialty.getJobTypes();
        List<Integer> jobTypeIds = new ArrayList<>();
        List<String> jobTypeNames = new ArrayList<>();
        for(JobType jobType : jobTypes){
            jobTypeIds.add(jobType.getUid());
            jobTypeNames.add(jobType.getName());
        }
        view.showJobTypeDialog(jobTypeIds , jobTypeNames);
    }

    /**
     * A list that represents the technicians that are showed on the view
     */
    List<Technician> techniciansFound = new ArrayList<>();
    /**
     * Selects the jobType to search for
     * @param jobTypeId
     */
    public void selectJobType(int jobTypeId){
        List<Technician> technicians =  technicianDAO.findAll();
        for(Technician technician : technicians){
            if(offersJobType(technician , jobTypeId)){
                techniciansFound.add(technician);
            }
        }
        repopulateTechnicianList();

    }
    public List<String> getAllAreas(){
        return areaDAO.getAreas();
    }
    public void filterArea(String area){
        List<Technician> tempTechnicians = new ArrayList<>();
        for(Technician technician : techniciansFound){
            if(technician.servesArea(area)) {
                tempTechnicians.add(technician);
            }
        }
        techniciansFound = tempTechnicians;
        //Refreshing the technicianList
        repopulateTechnicianList();

    }

    private void repopulateTechnicianList() {
        List<Integer> technicianIds = new ArrayList<>();
        //TODO Get average ratings from a technician reference for a jobtype or whatever
        List<Double> averageTechnicianRatings = new ArrayList<>();
        List<String> technicianNames = new ArrayList<>();
        List<Double> averageRatings = new ArrayList<>();
        //TODO Get price for a job type from a technician
        List<Double> prices = new ArrayList<>();
        for(Technician technician : techniciansFound){
            technicianIds.add(technician.getUid());
            technicianNames.add(technician.getName());
            averageTechnicianRatings.add(0.0);
            prices.add(0.0);
        }
        view.populateTechnicianList(technicianIds , technicianNames , averageRatings , prices);
    }



    //TODO Move this in domain or dao ?
    private boolean offersJobType(Technician technician , int jobTypeId){
        for(Job job : technician.getJobs()){
            if(job.getJobType().getUid() == jobTypeId){
                return true;
            }
        }
        return false;
    }


}
