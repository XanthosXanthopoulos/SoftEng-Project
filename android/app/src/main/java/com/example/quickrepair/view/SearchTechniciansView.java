package com.example.quickrepair.view;

import java.util.List;

public interface SearchTechniciansView {
    /**
     *  Δείχνει ολους τους τυπους εργασιας για την ειδικότητα που διάλεξε ο χρήστης
     */
    public void showJobTypeFromSpecialty(List<String> jobTypeNames);
    /**
     *  Δείχνει μια λιστα με τους τεχνικους
     */
    public void populateTechnicianList(List<Double> technicianNames , List<Double> averageRatings ,
                                       List<Double> prices);
}
