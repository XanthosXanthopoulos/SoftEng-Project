package com.example.quickrepair.view.SearchTechnicians;

import java.util.List;

public class SearchTechniciansViewStub implements SearchTechniciansView {

    SearchTechniciansPresenter presenter;
    public void setPresenter(SearchTechniciansPresenter presenter){
        this.presenter = presenter;
        this.presenter.setView(this);
        this.presenter.onStart();
    }
    public void chooseSpecialty(int position){
        presenter.selectSpecialty(specialtyIds.get(position));
    }
    public void chooseJobType(int position){
        presenter.selectJobType(jobTypeIds.get(position));
    }
    public void setPrice(String price){
        presenter.setMaxPrice(price);
    }
    public void setArea(int position){
        presenter.setArea(areas.get(position));
    }
    public void setDate(String year , String month , String day){
        presenter.setDate(year , month, day);
    }
    public void onStart(){
        presenter.onStart();
    }
    public void chooseTechnician(int position){
        presenter.onTechnicianClick(technicianIds.get(position));
    }
    public void setLoggedInUser(int uid){
        presenter.setLoggedInUser(uid);
    }

    public boolean jobTypeSpinnerEnabled;
    @Override
    public void setJobTypeSpinnerEnabled(boolean b) {
        this.jobTypeSpinnerEnabled = b;
    }

    String lastMessage;
    @Override
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        lastMessage = errorMessage;
    }

    List<Integer> specialtyIds;
    List<String> specialtyNames;
    @Override
    public void setSpecialtiesSource(List<Integer> specialtyIds, List<String> specialtyNames) {
        this.specialtyIds = specialtyIds;
        this.specialtyNames = specialtyNames;
    }
    List<Integer> jobTypeIds;
    List<String> jobTypeNames;
    @Override
    public void setJobTypesSource(List<Integer> jobTypeIds, List<String> jobTypeNames) {
        this.jobTypeIds = jobTypeIds;
        this.jobTypeNames = jobTypeNames;
    }

    List<String> areas;
    @Override
    public void setAreasSource(List<String> areas) {
        this.areas = areas;
    }

    List<Integer> technicianIds;
    List<String> technicianNames;
    List<Double> averageRatings;
    List<Double> prices;
    @Override
    public void populateTechnicianList(List<Integer> technicianIds, List<String> technicianNames, List<Double> averageRatings, List<Double> prices) {
        this.technicianIds = technicianIds;
        this.technicianNames = technicianNames;
        this.averageRatings = averageRatings;
        this.prices = prices;
    }
    boolean navigatedToRequestRepair =false;
    int navYear = -1;
    int navMonth = -1;
    int navDay = -1;

    @Override
    public void navigateToRequestRepair(int technicianId, int jobTypeId , int year , int month , int dayOfMonth) {
        navigatedToRequestRepair = true;
        navYear = year;
        navMonth = month;
        navDay = dayOfMonth;
    }

    boolean navigatedToLogin = false;
    @Override
    public void navigateToLogin() {
        navigatedToLogin = true;
    }
}
