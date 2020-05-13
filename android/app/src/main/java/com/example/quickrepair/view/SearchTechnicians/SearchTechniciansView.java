package com.example.quickrepair.view.SearchTechnicians;

import java.util.List;

public interface SearchTechniciansView {
    /**
     *  Δείχνει ολους τους τυπους εργασιας για την ειδικότητα που διάλεξε ο χρήστης
     */
    public void showJobTypeFromSpecialty(List<String> jobTypeNames);
    /**
     *  Δείχνει μια λιστα με τους τεχνικους
     */
    public void populateTechnicianList(List<Integer> technicianIds ,  List<String> technicianNames
                                       , List<Double> averageRatings , List<Double> prices);

    /**
     * Δειχνει μια λιστα με τις διαθέσιμες ειδικότητες απο τις οποίες μπορεί να διαλέξει ο χρήστης.
     */
    public void showSpecialtyDialog(List<Integer> specialtyIds , List<String> specialtyNames );

    /**
     * Δειχνει μια λιστα με τους διαθέσιμους τύπους εργασίας που διάλεξε ο χρήστης.
     */
    public void showJobTypeDialog(List<Integer> jobTypeIds , List<String> jobTypeNames);

    /**
     * Προχωράει στην περιπτωση χρησης δημιουργία ραντεβού
     */
    public void navigateToRequestRepair();




}
