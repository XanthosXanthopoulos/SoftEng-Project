package com.example.quickrepair.view;

public interface HomePageView
{
    /**
     * Όταν πραγματοποιείται click στο κουμπι για login , ο χρηστης μεταφερεται στο κατάλληλο
     * activity
     */
    void login();

    /**
     * Όταν πραγματοποιείται click στο κουμπι για signup , ο χρηστης μεταφερεται στο κατάλληλο
     * activity
     */
    void signUp();

    /**
     * Όταν πραγματοποιείται click στο κουμπι για αναζήτηση τεχνικού μεταφερόμαστε στο κατάλληλο
     * activity
     */
    void searchTechnicians();

    /**
     * Αφού έχει ταυτοποιηθεί κάποιος χρήστης (τεχνικός η πελάτης) μπορεί να προβάλλει τα αιτήματα
     *
     */
    void viewRepairRequests();

}
