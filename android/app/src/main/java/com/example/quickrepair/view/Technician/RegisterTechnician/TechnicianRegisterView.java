package com.example.quickrepair.view.Technician.RegisterTechnician;

import com.example.quickrepair.domain.Specialty;

import java.util.List;

public interface TechnicianRegisterView
{
    /**
     * Set the name of the technician trying to register or edit.
     *
     * @param name The name of the technician.
     */
    void setName(String name);

    /**
     * Set the surname of the technician trying to register or edit.
     *
     * @param surname The surname of the technician.
     */
    void setSurname(String surname);

    /**
     * Set the phone number of the technician trying to register or edit.
     *
     * @param phoneNumber The phone number of the technician.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Set the email of the technician trying to register or edit.
     *
     * @param email The email of the technician.
     */
    void setEmail(String email);

    /**
     * Set the bank account number of the technician trying to register or edit.
     *
     * @param accountNumber The bank account number of the technician.
     */
    void setAccountNumber(String accountNumber);

    /**
     * Set the AFM of the technician trying to register or edit.
     *
     * @param AFM The AFM of the technician.
     */
    void setAFM(String AFM);

    /**
     * Set the username of the technician trying to register or edit.
     *
     * @param username The username of the technician.
     */
    void setUsername(String username);

    /**
     * Set the password of the technician trying to register or edit.
     *
     * @param password The password of the technician.
     */
    void setPassword(String password);

    /**
     * Set the speciality of the user trying to register or edit.
     *
     * @param specialityID The speciality id of the technician.
     */
    void setSpecialityID(int specialityID);

    /**
     * Get the name of the technician trying to register or edit.
     *
     * @return The name of the technician trying to register or edit.
     */
    String getName();

    /**
     * Get the surname of the technician trying to register or edit.
     *
     * @return The surname of the technician trying to register or edit.
     */
    String getSurname();

    /**
     * Get the phone number of the technician trying to register or edit.
     *
     * @return The phone number of the technician trying to register or edit.
     */
    String getPhoneNumber();

    /**
     * Get the email of the technician trying to register or edit.
     *
     * @return The email of the technician trying to register or edit.
     */
    String getEmail();

    /**
     * Get the bank account number of the technician trying to register or edit.
     *
     * @return The bank account number of the technician trying to register or edit.
     */
    String getAccountNumber();

    /**
     * Get the AFM of the technician trying to register or edit.
     *
     * @return The AFM of the technician trying to register or edit.
     */
    String getAFM();

    /**
     * Get the username of the technician trying to register or edit.
     *
     * @return The username of the technician trying to register or edit.
     */
    String getUsername();

    /**
     * Get the password of the technician trying to register or edit.
     *
     * @return The password of the technician trying to register or edit.
     */
    String getPassword();

    /**
     *  Get the speciality id of the technician trying to register or edit.
     *
     * @return The speciality id of the technician trying to register or edit.
     */
    Integer getSpecialityID();

    /**
     * Populate the list holding all available specialities.
     *
     * @param specialityList The list of specialities.
     * @param defaultName A default speciality placeholder.
     */
    void setSpecialityList(List<String> specialityList, String defaultName);

    /**
     * Navigate a valid technician to the technician schedule page.
     *
     * @param id The technician's id.
     */
    void onSuccessfulRegister(Integer id);

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    void showErrorMessage(String title, String message);
}
