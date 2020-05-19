package com.example.quickrepair.view.Technician.AddEditJob;

import com.example.quickrepair.domain.Job;

import java.util.List;

public interface AddEditJobView
{
    void setJobTypeList(List<String> areaList, String defaultName);

    void setJobList(List<Job> selectedJobs);

    void showErrorMessage(String title, String message);
}
