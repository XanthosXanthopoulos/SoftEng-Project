package com.example.quickrepair.view.Technician.AddEditArea;

import java.util.List;

public class AddEditAreaViewStub implements AddEditAreaView
{
    private String errorTitle;
    private String errorMessage;

    @Override
    public void setAreaList(List<String> areaList, String defaultName)
    {

    }

    @Override
    public void setSelectedArea(List<String> selectedArea)
    {

    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }

    public String getErrorTitle()
    {
        return errorTitle;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
