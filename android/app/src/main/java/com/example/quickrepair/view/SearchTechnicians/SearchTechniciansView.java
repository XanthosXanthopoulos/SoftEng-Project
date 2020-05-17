package com.example.quickrepair.view.SearchTechnicians;

import java.util.List;

public interface SearchTechniciansView {
    /**
     *  Θέτει τον spinner των τύπων εργασίας ως ενεργοποιημένο
     */
    public void setJobTypeSpinnerEnabled(boolean b);
    /**
     *  Δείχνει ένα μήνυμα λάθους
     */
    public void showErrorMessage(String errrorMessage);
    /**
     *  Ορίζει τις ειδικότητες που μπορουν να εμφανιστούν στον spinner
     */
    public void setSpecialtiesSource(List<Integer> specialtyIds , List<String> specialtyNames);
    /**
     *  Ορίζει τους τύπους εργασίας που μπορούν να εμφανιστούν στον spinner
     */
    public void setJobTypesSource(List<Integer> jobTypeIds , List<String> jobTypeNames);
    /**
     *  Ορίζει την λίστα απο περιοχές απο την οποία μπορεί να διαλέξει ο χρήστης
     */
    public void setAreasSource(List<String> areas);

    /**
     *  Γεμίζει την λίστα με τεχνικούς
     */
    public void populateTechnicianList(List<Integer> technicianIds ,  List<String> technicianNames
                                       , List<Double> averageRatings , List<Double> prices);

    /**
     * Προχωράει στην περιπτωση χρησης δημιουργία ραντεβού
     */
    public void navigateToRequestRepair();




}
