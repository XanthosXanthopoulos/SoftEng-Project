package com.example.quickrepair.view.Technician.RepairRequests;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.view.HomePage.HomePagePresenter;

public class TechnicianRepairRequestsViewModel extends ViewModel {
    private TechnicianRepairRequestsPresenter presenter;

    public TechnicianRepairRequestsViewModel(){
        super();
        //create Presenter
        presenter = new TechnicianRepairRequestsPresenter();
    }

    public TechnicianRepairRequestsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reason
        presenter.clearView();
    }
}