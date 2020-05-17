package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.domain.Specialty;

import java.util.List;

public interface TechnicianRegisterView
{
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
