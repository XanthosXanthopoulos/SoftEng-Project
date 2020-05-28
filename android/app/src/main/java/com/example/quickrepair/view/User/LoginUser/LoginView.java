package com.example.quickrepair.view.User.LoginUser;

public interface LoginView
{
    /**
     * Get the username of the user trying to log in.
     *
     * @return The username of the user trying to log in.
     */
    String getUsername();

    /**
     * Get the password of the user trying to log in.
     *
     * @return The password of the user trying to log in.
     */
    String getPassword();

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    void showErrorMessage(String title, String message);

    /**
     * Navigate a valid customer to the customer home page.
     *
     * @param id The customer's id.
     */
    void OnLoginCustomerSuccess(Integer id);

    /**
     * Navigate a valid technician to the technician home page.
     *
     * @param id The technician's id.
     */
    void OnLoginTechnicianSuccess(Integer id);
}
