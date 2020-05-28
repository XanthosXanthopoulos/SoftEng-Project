package com.example.quickrepair.view.Technician.AddEditArea;

import java.util.List;

public interface AddEditAreaView
{
    /**
     * Populate the list holding all available areas.
     *
     * @param areaList The list of areas.
     * @param defaultName A default area placeholder.
     */
    void setAreaList(List<String> areaList, String defaultName);

    /**
     * Populate the list holding the technician's areas.
     *
     * @param selectedArea The list of the technician's areas.
     */
    void setSelectedArea(List<String> selectedArea);

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    void showErrorMessage(String title, String message);
}
