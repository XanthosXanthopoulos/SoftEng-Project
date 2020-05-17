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
        presenter.filterByMaxPrice(price);
    }
    public void setArea(int position){
        presenter.filterArea(areas.get(position));
    }
    public void onStart(){
        presenter.onStart();
    }

    public boolean jobTypeSpinnerEnabled;
    @Override
    public void setJobTypeSpinnerEnabled(boolean b) {
        this.jobTypeSpinnerEnabled = b;
    }

    String lastMessage;
    @Override
    public void showErrorMessage(String errrorMessage) {
        lastMessage = errrorMessage;
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
    boolean navigatedToRequestRepair;
    @Override
    public void navigateToRequestRepair() {
        navigatedToRequestRepair = true;
    }
}
