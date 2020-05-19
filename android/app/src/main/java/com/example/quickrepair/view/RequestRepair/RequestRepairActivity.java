package com.example.quickrepair.view.RequestRepair;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quickrepair.R;

import java.util.List;

public class RequestRepairActivity extends AppCompatActivity implements RequestRepairView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_repair);
    }

    @Override
    public void setTechnicianPhoneNumber(String text) {

    }

    @Override
    public void setTechnicianName(String text) {

    }

    @Override
    public void setJobTypeName(String text) {

    }

    @Override
    public void showTimesAvailable(List<String> availableHours) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this , error , Toast.LENGTH_SHORT).show();
    }
}
