package com.example.quickrepair.view.RequestRepair;

import android.widget.ArrayAdapter;

import com.example.quickrepair.dao.CustomerDAO;
import com.example.quickrepair.dao.JobTypeDAO;
import com.example.quickrepair.dao.RepairRequestDAO;
import com.example.quickrepair.dao.TechnicianDAO;
import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;
import com.example.quickrepair.util.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class RequestRepairPresenter {
    private RequestRepairView view;
    private TechnicianDAO technicianDAO;
    private JobTypeDAO jobTypeDAO;
    private CustomerDAO customerDAO;
    private RepairRequestDAO repairRequestDAO;
    public RequestRepairPresenter(TechnicianDAO technicianDAO , JobTypeDAO jobTypeDAO , CustomerDAO customerDAO , RepairRequestDAO repairRequestDAO){
        this.technicianDAO = technicianDAO;
        this.jobTypeDAO = jobTypeDAO;
        this.customerDAO = customerDAO;
        this.repairRequestDAO  = repairRequestDAO;
    }
    public void setView(RequestRepairView view){
        this.view = view;
    }
    //PARAMETERS PASSED FROM PREVIOUS ACTIVITIES
    Technician selectedTechnician;
    //TODO Throw exception if invalid data has been passed by other activities
    public void setTechnicianId(int technicianId){
        selectedTechnician = technicianDAO.find(technicianId);
    }
    JobType selectedJobType;
    public void setJobTypeId(int jobTypeId){
        selectedJobType = jobTypeDAO.find(jobTypeId);
    }
    Customer loggedInUser;
    public void setLoggedInUser(int userId){
        loggedInUser =  customerDAO.find(userId);
    }
    int year;
    int month;
    int day;
    public void setDate(int year , int month , int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    //Inputs from user during activity
    private String selectedComments = "";
    public void setComments(String comments){
        if(comments != null) this.selectedComments = comments;
    }
    private Address selectedAddress = null;
    public void setAddress(String address){
        try{
            String[] tokens = address.split(",");
            Integer.parseInt(tokens[1].trim());
            selectedAddress = new Address(tokens[0].trim() , tokens[1].trim());
        }
        catch (Exception e){
            view.showError("Please enter an address in the correct form e.g. your_address_name , number ");
            return;
        }
    }
    int hourOfDay = -1;
    int minutes = -1;
    public void setTime(int hourOfDay , int minutes){
        if(hourOfDay <24 && hourOfDay >= 0 && minutes < 60 && minutes >= 0 ){
            if(!timeInRange(hourOfDay,minutes , selectedTechnician , year , month, day)){
                view.showError("The technician is not available on that time. Please enter an alternate " +
                        "time");
                return;
            }
            this.hourOfDay = hourOfDay;
            this.minutes = minutes;
        }
        else{
            view.showError("Please enter a time between 0:00 and 23:59");
            return;
        }
    }
    public void onStart(){
        view.setJobTypeName(selectedJobType.getName());
        view.setTechnicianName(selectedTechnician.getName());
        view.setTechnicianPhoneNumber(selectedTechnician.getPhoneNumber());
        ArrayList<ArrayList<GregorianCalendar>> hourRanges = selectedTechnician.getAvailableHourRanges(new GregorianCalendar(year , month , day ,2 , 2));
        List<String> timesAvailableForView = createListFromHourRanges(selectedTechnician);
        view.showTimesAvailable(timesAvailableForView);

    }
    public void requestRepair(){
        GregorianCalendar dateNow = (GregorianCalendar) Calendar.getInstance();
        GregorianCalendar date = null;
        if(hourOfDay == -1 || minutes == -1){
            return;
        }
        date = new GregorianCalendar(year , month , day , hourOfDay , minutes);
        Job job = getJobFromJobType(selectedTechnician , selectedJobType);
        String comments = selectedComments;
        if(selectedAddress == null){

            return;
        }
        Address address = selectedAddress;
        RepairRequest  result = loggedInUser.requestRepair(dateNow , date , job , comments , address);
        //Saving the created repair request to the DAO
        repairRequestDAO.save(result);
        view.showTimesAvailable(createListFromHourRanges(selectedTechnician));
        view.exit();
        view.showInfo("Successfully created a repair request!");
    }


    Job getJobFromJobType(Technician technician , JobType jobType){
        for(Job job : technician.getJobs()){
            if(job.getJobType().getUid() == jobType.getUid()){
                return  job;
            }
        }
        return  null;
    }

    /**
     * Returns true if the input time is between the range of the calendars (taking into consideration
     * only the hourofday and minutes)
     */
    boolean timeInRange(int hourOfDay , int minutes , GregorianCalendar start , GregorianCalendar end){
         int year = start.get(Calendar.YEAR);
         int month = start.get(Calendar.MONTH);
         int dayOfMonth = start.get(Calendar.DAY_OF_MONTH);


         GregorianCalendar target = new GregorianCalendar(year , month , dayOfMonth , hourOfDay , minutes);
         //Using gregoriancalendars builtin compare to test if the target is between the range
         // of the calendars

         return target.compareTo(end) < 0 && target.compareTo(start) > 0;

    }

    /**
     * Checks if the techncician is available on the certain time
     */
    boolean timeInRange(int hourOfDay , int minutes , Technician technician , int year , int month, int dayOfMonth){
        ArrayList<ArrayList<GregorianCalendar>> hourRanges =
                technician.getAvailableHourRanges(new GregorianCalendar(year , month , dayOfMonth , 2 , 2));
        for(ArrayList<GregorianCalendar> list : hourRanges){
            if(timeInRange(hourOfDay, minutes , list.get(0) , list.get(1))){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a list to print from the hour ranges of the technician
     */
    List<String> createListFromHourRanges(Technician technician){
        ArrayList<ArrayList<GregorianCalendar>> hourRanges = technician.getAvailableHourRanges(new GregorianCalendar(year , month , day , 2 , 2));
        List<String> timesAvailableForView = new ArrayList<>();
        for(ArrayList<GregorianCalendar> calendars : hourRanges){
            GregorianCalendar start = calendars.get(0);
            GregorianCalendar end = calendars.get(1);
            int startHour = start.get(Calendar.HOUR_OF_DAY);
            int startMinutes = start.get(Calendar.MINUTE);
            int endHour = end.get(Calendar.HOUR_OF_DAY);
            int endMinutes = end.get(Calendar.MINUTE);
            String result = startHour + ":" + startMinutes + " - " + endHour + ":" + endMinutes;

            start.add(Calendar.DAY_OF_MONTH , 1);
            int difference = start.compareTo(end);
            if(difference >= 0 && endHour == 0){
                //This means the end gap was set to 24 so the calendar was rolled forward a day
                result = startHour + ":" + startMinutes + " - 23:59";
            }
            timesAvailableForView.add(result);
        }
        return timesAvailableForView;
    }
}
