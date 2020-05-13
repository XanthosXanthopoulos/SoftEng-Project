package com.example.quickrepair.view.HomePage;

public class HomePagePresenter {
    private HomePageView view;

    public HomePagePresenter(){}

    public void setView(HomePageView view) {
        this.view = view;
    }
    public void onSearchSelected(){
        view.searchTechnicians();
    }
    public void onLogInSelected(){
        view.login();
    }
    public void onRegisterAsTechnicianSelected(){
        view.registerAsTechnician();
    }

    public void onRegisterAsCustomerSelected(){
        view.registerAsCustomer();
    }

    public void clearView(){
        this.view = null;
    }

}
