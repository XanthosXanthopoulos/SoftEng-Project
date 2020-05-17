package com.example.quickrepair.view.User.LoginUser;

public interface LoginView
{
    String getUsername();
    String getPassword();

    void showErrorMessage(String title, String message);

    void OnLoginCustomerSuccess(Integer id);

    void OnLoginTechnicianSuccess(Integer id);
}
