package com.example.quickrepair.view.Technician.RegisterTechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quickrepair.R;

import java.util.List;

public class TechnicianRegisterActivity extends AppCompatActivity implements TechnicianRegisterView
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_register);
    }

    @Override
    public String getName()
    {
        return ((EditText)findViewById(R.id.Name)).getText().toString().trim();
    }

    @Override
    public String getSurname()
    {
        return ((EditText)findViewById(R.id.Surname)).getText().toString().trim();
    }

    @Override
    public String getPhoneNumber()
    {
        return ((EditText)findViewById(R.id.PhoneNumber)).getText().toString().trim();
    }

    @Override
    public String getAccountNumber()
    {
        return ((EditText)findViewById(R.id.BankAccount)).getText().toString().trim();
    }

    @Override
    public String getEmail()
    {
        return ((EditText)findViewById(R.id.Email)).getText().toString().trim();
    }

    @Override
    public String getUsername()
    {
        return ((EditText)findViewById(R.id.Username)).getText().toString().trim();
    }

    @Override
    public String getPassword()
    {
        return ((EditText)findViewById(R.id.Password)).getText().toString().trim();
    }

    @Override
    public String getAFM()
    {
        return ((EditText)findViewById(R.id.AFM)).getText().toString().trim();
    }

    @Override
    public Integer getSpecialityID()
    {
        return null;
    }

    @Override
    public void setSpecialityList(List<String> specialityList, String defaultName)
    {
        specialityList.add(0, defaultName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specialityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner)findViewById(R.id.Speciality)).setAdapter(adapter);
    }

    @Override
    public void onSuccessfulRegister(Integer id)
    {

    }

    @Override
    public void showErrorMessage(String title, String message)
    {

    }
}
