package com.example.quickrepair.view.Technician.RegisterTechnician;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quickrepair.R;
import com.example.quickrepair.view.HomePage.HomePageActivity;
import com.example.quickrepair.view.Technician.AddEditSchedule.AddEditScheduleActivity;

import java.util.List;

import static com.example.quickrepair.QuickRepairApplication.TECHNICIAN_ID_EXTRA;

public class TechnicianRegisterActivity extends AppCompatActivity implements TechnicianRegisterView
{
    TechnicianRegisterViewModel viewModel;

    /**
     * Create and initialize the activity.
     *
     * @param savedInstanceState The activity's saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_register);

        viewModel = new ViewModelProvider(this).get(TechnicianRegisterViewModel.class);
        final TechnicianRegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        int technicianID = getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0);
        presenter.setTechnician(technicianID);
        presenter.setUpDataSource();

        if (technicianID != 0)
        {
            findViewById(R.id.Username).setEnabled(false);
            findViewById(R.id.Username).setFocusable(false);
            ((EditText)findViewById(R.id.Username)).setInputType(InputType.TYPE_NULL);
            ((TextView)findViewById(R.id.Title)).setText("Profile");
            ((Button)findViewById(R.id.RegisterButton)).setText("Save");
        }

        findViewById(R.id.RegisterButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.registerTechnician(getName(), getSurname(), getPhoneNumber(), getEmail(), getAFM(), getAccountNumber(), getUsername(), getPassword(), getSpecialityID());
            }
        });
    }

    /**
     * Set the name of the technician trying to register or edit.
     *
     * @param name The name of the technician.
     */
    @Override
    public void setName(String name)
    {
        ((EditText) findViewById(R.id.Name)).setText(name);
    }

    /**
     * Set the surname of the technician trying to register or edit.
     *
     * @param surname The surname of the technician.
     */
    @Override
    public void setSurname(String surname)
    {
        ((EditText) findViewById(R.id.Surname)).setText(surname);
    }

    /**
     * Set the phone number of the technician trying to register or edit.
     *
     * @param phoneNumber The phone number of the technician.
     */
    @Override
    public void setPhoneNumber(String phoneNumber)
    {
        ((EditText) findViewById(R.id.PhoneNumber)).setText(phoneNumber);
    }

    /**
     * Set the email of the technician trying to register or edit.
     *
     * @param email The email of the technician.
     */
    @Override
    public void setEmail(String email)
    {
        ((EditText) findViewById(R.id.Email)).setText(email);
    }

    /**
     * Set the bank account number of the technician trying to register or edit.
     *
     * @param accountNumber The bank account number of the technician.
     */
    @Override
    public void setAccountNumber(String accountNumber)
    {
        ((EditText) findViewById(R.id.BankAccount)).setText(accountNumber);
    }

    /**
     * Set the AFM of the technician trying to register or edit.
     *
     * @param AFM The AFM of the technician.
     */
    @Override
    public void setAFM(String AFM)
    {
        ((EditText) findViewById(R.id.AFM)).setText(AFM);
    }

    /**
     * Set the username of the technician trying to register or edit.
     *
     * @param username The username of the technician.
     */
    @Override
    public void setUsername(String username)
    {
        ((EditText) findViewById(R.id.Username)).setText(username);
    }

    /**
     * Set the password of the technician trying to register or edit.
     *
     * @param password The password of the technician.
     */
    @Override
    public void setPassword(String password)
    {
        ((EditText) findViewById(R.id.Password)).setText(password);
    }

    /**
     * Set the speciality of the user trying to register or edit.
     *
     * @param specialityID The speciality id of the technician.
     */
    @Override
    public void setSpecialityID(int specialityID)
    {
        ((Spinner) findViewById(R.id.Speciality)).setSelection(specialityID);
    }

    /**
     * Get the name of the technician trying to register or edit.
     *
     * @return The name of the technician trying to register or edit.
     */
    @Override
    public String getName()
    {
        return ((EditText) findViewById(R.id.Name)).getText().toString().trim();
    }

    /**
     * Get the surname of the technician trying to register or edit.
     *
     * @return The surname of the technician trying to register or edit.
     */
    @Override
    public String getSurname()
    {
        return ((EditText) findViewById(R.id.Surname)).getText().toString().trim();
    }

    /**
     * Get the phone number of the technician trying to register or edit.
     *
     * @return The phone number of the technician trying to register or edit.
     */
    @Override
    public String getPhoneNumber()
    {
        return ((EditText) findViewById(R.id.PhoneNumber)).getText().toString().trim();
    }

    /**
     * Get the bank account number of the technician trying to register or edit.
     *
     * @return The bank account number of the technician trying to register or edit.
     */
    @Override
    public String getAccountNumber()
    {
        return ((EditText) findViewById(R.id.BankAccount)).getText().toString().trim();
    }

    /**
     * Get the email of the technician trying to register or edit.
     *
     * @return The email of the technician trying to register or edit.
     */
    @Override
    public String getEmail()
    {
        return ((EditText) findViewById(R.id.Email)).getText().toString().trim();
    }

    /**
     * Get the username of the technician trying to register or edit.
     *
     * @return The username of the technician trying to register or edit.
     */
    @Override
    public String getUsername()
    {
        return ((EditText) findViewById(R.id.Username)).getText().toString().trim();
    }

    /**
     * Get the password of the technician trying to register or edit.
     *
     * @return The password of the technician trying to register or edit.
     */
    @Override
    public String getPassword()
    {
        return ((EditText) findViewById(R.id.Password)).getText().toString().trim();
    }

    /**
     * Get the AFM of the technician trying to register or edit.
     *
     * @return The AFM of the technician trying to register or edit.
     */
    @Override
    public String getAFM()
    {
        return ((EditText) findViewById(R.id.AFM)).getText().toString().trim();
    }

    /**
     *  Get the speciality id of the technician trying to register or edit.
     *
     * @return The speciality id of the technician trying to register or edit.
     */
    @Override
    public Integer getSpecialityID()
    {
        return ((Spinner) findViewById(R.id.Speciality)).getSelectedItemPosition();
    }

    /**
     * Populate the list holding all available specialities.
     *
     * @param specialityList The list of specialities.
     * @param defaultName A default speciality placeholder.
     */
    @Override
    public void setSpecialityList(List<String> specialityList, String defaultName)
    {
        specialityList.add(0, defaultName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specialityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner) findViewById(R.id.Speciality)).setAdapter(adapter);
    }

    /**
     * Navigate a valid technician to the technician schedule page.
     *
     * @param id The technician's id.
     */
    @Override
    public void onSuccessfulRegister(Integer id)
    {
        Intent intent = new Intent(this, AddEditScheduleActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, id);
        startActivity(intent);

        finish();
    }

    /**
     * Display a message in the event of an error.
     *
     * @param title The title of the error.
     * @param message The message of the error.
     */
    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Handle on back button press event.
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);

        finish();
    }
}
