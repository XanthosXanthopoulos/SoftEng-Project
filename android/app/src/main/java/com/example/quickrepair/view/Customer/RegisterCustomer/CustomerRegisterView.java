package com.example.quickrepair.view.Customer.RegisterCustomer;

import java.util.List;

public interface CustomerRegisterView
{
    String getName();
    String getSurname();
    String getPhoneNumber();
    String getBankAccountNumber();
    String getEmail();
    String getUsername();
    String getPassword();

    void onSuccessfulRegister(Integer id);

    void showErrorMessage(String title, String message);
}
