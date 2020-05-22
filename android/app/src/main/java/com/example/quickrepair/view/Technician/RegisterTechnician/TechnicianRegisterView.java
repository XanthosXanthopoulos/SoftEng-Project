package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.domain.Specialty;

import java.util.List;

public interface TechnicianRegisterView
{
    void setName(String name);
    void setSurname(String surname);
    void setPhoneNumber(String phoneNumber);
    void setEmail(String email);
    void setAccountNumber(String accountNumber);
    void setAFM(String AFM);
    void setUsername(String username);
    void setPassword(String password);
    void setSpecialityID(int specialityID);

    String getName();
    String getSurname();
    String getPhoneNumber();
    String getEmail();
    String getAccountNumber();
    String getAFM();
    String getUsername();
    String getPassword();
    Integer getSpecialityID();

    void setSpecialityList(List<String> specialityList, String defaultName);

    void onSuccessfulRegister(Integer id);

    void showErrorMessage(String title, String message);
}
