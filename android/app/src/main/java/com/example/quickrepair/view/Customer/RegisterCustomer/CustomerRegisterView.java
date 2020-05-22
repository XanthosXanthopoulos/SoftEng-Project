package com.example.quickrepair.view.Customer.RegisterCustomer;

import java.util.List;

public interface CustomerRegisterView
{
    void setName(String name);
    void setSurname(String surname);
    void setPhoneNumber(String phoneNumber);
    void setBankAccountNumber(String accountNumber);
    void setEmail(String email);
    void setUsername(String username);
    void setPassword(String password);

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
