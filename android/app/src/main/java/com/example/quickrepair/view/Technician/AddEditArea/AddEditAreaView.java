package com.example.quickrepair.view.Technician.AddEditArea;

import java.util.List;

public interface AddEditAreaView
{
    void setAreaList(List<String> areaList, String defaultName);

    void setSelectedArea(List<String> selectedArea);

    void showErrorMessage(String title, String message);
}
