package com.example.quickrepair.view.RequestRepair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickrepair.R;
import com.example.quickrepair.view.SearchTechnicians.SearchTechniciansViewModel;

import java.util.List;

public class RequestRepairActivity extends AppCompatActivity implements RequestRepairView {
    RequestRepairViewModel viewModel;
    RequestRepairPresenter presenter;

    //Parameters of the activity to be passed on startup
    int year;
    int month;
    int dayOfMonth;

    int loggedInCustomerId;

    int technicianId;
    int jobTypeId;

    ListView timesList;
    EditText addressText;
    EditText commentsText;
    NumberPicker hourNumber;
    NumberPicker minuteNumber;

    Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_repair);
        viewModel = new ViewModelProvider(this).get(RequestRepairViewModel.class);
        presenter = viewModel.getPresenter();
        presenter.setView(this);
        //Reading parameters from the intent that created the activity
        //TODO Integrate with search technicians
        /*
        Intent intent = getIntent();
        year = intent.getIntExtra("year" , -1);
        month = intent.getIntExtra("month" , -1);
        dayOfMonth =  intent.getIntExtra("dayOfMonth" , -1);
        loggedInCustomerId =  intent.getIntExtra("loggedInCustomerId" , -1);
        technicianId = intent.getIntExtra("technicianId" , -1);
        jobTypeId = intent.getIntExtra("jobTypeId" , -1);
         */
        year = 2012;
        month = 5;
        dayOfMonth = 3;
        loggedInCustomerId = 1;
        technicianId = 1;
        jobTypeId = 1;
        presenter.setDate(year , month , dayOfMonth);
        //TODO Check if user logged in is a customer
        presenter.setLoggedInUser(loggedInCustomerId);
        presenter.setJobTypeId(jobTypeId);
        presenter.setTechnicianId(technicianId);

        timesList = findViewById(R.id.times_list);
        addressText = (EditText) findViewById(R.id.address);
        commentsText = (EditText) findViewById(R.id.comments);
        hourNumber = findViewById(R.id.hour);
        minuteNumber = findViewById(R.id.minutes);
        hourNumber.setMaxValue(23);
        hourNumber.setMinValue(0);
        minuteNumber.setMaxValue(59);
        minuteNumber.setMinValue(0);

        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setComments(commentsText.getText().toString());
                presenter.setTime(hourNumber.getValue() , minuteNumber.getValue());
                presenter.setAddress(addressText.getText().toString());
                presenter.requestRepair();
            }
        });
        presenter.onStart();
        //TODO onclicks for buttons

    }

    @Override
    public void setTechnicianPhoneNumber(String text) {
        ((TextView) findViewById(R.id.phone_number)).setText(text);
    }

    @Override
    public void setTechnicianName(String text) {
        ((TextView) findViewById(R.id.technician_name)).setText(text);
    }

    @Override
    public void setJobTypeName(String text) {
        ((TextView) findViewById(R.id.job_type_name)).setText(text);
    }

    @Override
    public void showTimesAvailable(List<String> availableHours) {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item , availableHours);
        timesList.setAdapter(listAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this , error , Toast.LENGTH_SHORT).show();
    }
}
