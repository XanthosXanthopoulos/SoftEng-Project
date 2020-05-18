package com.example.quickrepair.view.SearchTechnicians;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.memorydao.MemoryInitializer;

public class SearchTechniciansViewModel extends ViewModel {

    SearchTechniciansPresenter presenter;

    public SearchTechniciansViewModel() {

        //Where do i get the daos ? TODO
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        presenter = new SearchTechniciansPresenter(initializer.getTechnicianDAO() , initializer.getSpecialtyDAO()
        , initializer.getJobTypeDAO() , initializer.getAreaDAO());

    }


    public SearchTechniciansPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // clear view instance to avoid leaking activity , i dont understand what i am supposed to do
        //TODO
        //presenter.clearView();
    }
}
