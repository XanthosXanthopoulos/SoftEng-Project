package com.example.quickrepair.view.Technician.AddEditJob;

import com.example.quickrepair.domain.Job;

import java.util.List;

public interface AddEditJobView
{
    /**
     * Populate the list holding all available job types.
     *
     * @param jobTypeList The list of job types.
     * @param defaultName A default job type placeholder.
     */
    void setJobTypeList(List<String> jobTypeList, String defaultName);

    /**
     * Populate the list holding the technician's job types.
     *
     * @param selectedJobs The list of the technician's job types.
     */
    void setJobList(List<Job> selectedJobs);

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    void showErrorMessage(String title, String message);
}
