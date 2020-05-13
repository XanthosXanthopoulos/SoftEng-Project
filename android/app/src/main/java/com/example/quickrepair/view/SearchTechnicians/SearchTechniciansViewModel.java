package com.example.quickrepair.view.SearchTechnicians;

import androidx.lifecycle.ViewModel;

public class SearchTechniciansViewModel extends ViewModel {

    SearchTechniciansPresenter presenter;

    public SearchTechniciansViewModel() {

        // assemble presenter here todo
        //presenter = new SearchTechniciansPresenter();
        //

    }


    public SearchTechniciansPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // clear view instance to avoid leaking activity
        //TODO
        //presenter.clearView();
    }
}
