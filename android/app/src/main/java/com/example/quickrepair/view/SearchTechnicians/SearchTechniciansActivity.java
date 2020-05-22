package com.example.quickrepair.view.SearchTechnicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quickrepair.QuickRepairApplication;
import com.example.quickrepair.R;
import com.example.quickrepair.view.RequestRepair.RequestRepairActivity;
import com.example.quickrepair.view.User.LoginUser.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.quickrepair.QuickRepairApplication.CUSTOMER_ID_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.DAY_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.JOBTYPE_ID_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.MONTH_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.REDIRECT_TO_SEARCH_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.REQUEST_CODE_LOGIN;
import static com.example.quickrepair.QuickRepairApplication.TECHNICIAN_ID_EXTRA;
import static com.example.quickrepair.QuickRepairApplication.YEAR_EXTRA;

public class SearchTechniciansActivity extends AppCompatActivity implements SearchTechniciansView
{

    SearchTechniciansViewModel viewModel;
    SearchTechniciansPresenter presenter;

    Spinner specialtySpinner;
    Spinner jobTypeSpinner;
    Spinner areaSpinner;
    EditText maxpriceText;

    Intent pendingLogin;

    ArrayAdapter<SpinnerEntry> specialtyAdapter;
    ArrayAdapter<SpinnerEntry> jobTypeAdapter;
    ArrayAdapter<String> areaAdapter;

    EditText yearText;
    EditText monthText;
    EditText dayText;

    TechnicianAdapter technicianAdapter;
    ListView techniciansList;

    int loggedInUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_technicians);
        Intent intent = getIntent();
        loggedInUserId = intent.getIntExtra(QuickRepairApplication.LOGGED_IN_USER_ID_EXTRA, -1);


        viewModel = new ViewModelProvider(this).get(SearchTechniciansViewModel.class);
        presenter = viewModel.getPresenter();
        presenter.setView(this);

        if (loggedInUserId >= 0)
        {
            presenter.setLoggedInUser(loggedInUserId);
        }


        //Setting spinner adapters
        specialtySpinner = findViewById(R.id.specialty_spinner);
        jobTypeSpinner = findViewById(R.id.job_type_spinner);
        areaSpinner = findViewById(R.id.area_spinner);

        specialtyAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        jobTypeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        areaAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);

        maxpriceText = findViewById(R.id.max_price_text);
        yearText = findViewById(R.id.year);
        monthText = findViewById(R.id.month);
        dayText = findViewById(R.id.day);

        specialtySpinner.setAdapter(specialtyAdapter);
        jobTypeSpinner.setAdapter(jobTypeAdapter);
        areaSpinner.setAdapter(areaAdapter);

        specialtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SpinnerEntry entry = (SpinnerEntry) parent.getAdapter().getItem(position);
                presenter.selectSpecialty(entry.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //Setting technicianListAdapters
        techniciansList = findViewById(R.id.technician_list);
        technicianAdapter = new TechnicianAdapter(this, R.layout.search_technicians_technician_list_layout);
        techniciansList.setAdapter(technicianAdapter);

        Button b = findViewById(R.id.search_button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("w", "view button clicked");
                SpinnerEntry entry = (SpinnerEntry) jobTypeSpinner.getSelectedItem();
                int selectedJobTypeId = entry.getId();
                String selectedMaxPrice = maxpriceText.getText().toString();
                String selectedArea = (String) areaSpinner.getSelectedItem();

                String month = monthText.getText().toString();
                String year = yearText.getText().toString();
                String day = dayText.getText().toString();

                presenter.setDate(year, month, day);
                presenter.selectJobType(selectedJobTypeId);
                presenter.setMaxPrice(selectedMaxPrice);
                presenter.setArea(selectedArea);
            }
        });
        techniciansList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.e("test", "Clicked on technician id  " + id);
                presenter.onTechnicianClick((int) id);
            }
        });
        presenter.onStart();

    }

    @Override
    public void setJobTypeSpinnerEnabled(boolean b)
    {
        findViewById(R.id.job_type_spinner).setEnabled(b);
    }

    @Override
    public void showErrorMessage(String errorMessage)
    {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSpecialtiesSource(List<Integer> specialtyIds, List<String> specialtyNames)
    {
        List<SpinnerEntry> list = new ArrayList<>();
        for (int i = 0; i < specialtyIds.size(); i++)
        {
            list.add(new SpinnerEntry(specialtyIds.get(i), specialtyNames.get(i)));
        }
        specialtyAdapter = new ArrayAdapter<>(this
                , R.layout.support_simple_spinner_dropdown_item, list);
        specialtySpinner.setAdapter(specialtyAdapter);

    }

    @Override
    public void setJobTypesSource(List<Integer> jobTypeIds, List<String> jobTypeNames)
    {
        System.out.println("Setting specialties source" + jobTypeNames);
        List<SpinnerEntry> list = new ArrayList<>();
        for (int i = 0; i < jobTypeIds.size(); i++)
        {
            list.add(new SpinnerEntry(jobTypeIds.get(i), jobTypeNames.get(i)));
        }
        jobTypeAdapter = new ArrayAdapter<>(this
                , R.layout.support_simple_spinner_dropdown_item, list);
        jobTypeSpinner.setAdapter(jobTypeAdapter);
    }

    @Override
    public void setAreasSource(List<String> areas)
    {
        areaAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, areas);
        areaSpinner.setAdapter(areaAdapter);
    }

    @Override
    public void populateTechnicianList(List<Integer> technicianIds, List<String> technicianNames, List<Double> averageRatings, List<Double> prices)
    {
        Log.e("Pop", "Populating tech list with " + technicianIds);
        System.out.println("tech size " + technicianIds.size());
        System.out.println("names size " + technicianNames.size());
        System.out.println("avg rat " + averageRatings.size());
        System.out.println("prices z" + prices.size());
        TechnicianAdapter technicianAdapter = new TechnicianAdapter(this, 2);
        technicianAdapter.setSource(technicianIds, technicianNames, averageRatings, prices);
        techniciansList.setAdapter(technicianAdapter);
    }

    @Override
    public void navigateToRequestRepair(int technicianId, int jobTypeId, int year, int month, int dayOfMonth)
    {
        pendingLogin = new Intent(this, RequestRepairActivity.class);
        pendingLogin.putExtra(TECHNICIAN_ID_EXTRA, technicianId);
        pendingLogin.putExtra(JOBTYPE_ID_EXTRA, jobTypeId);
        pendingLogin.putExtra(YEAR_EXTRA, year);
        pendingLogin.putExtra(MONTH_EXTRA, month);
        pendingLogin.putExtra(DAY_EXTRA, dayOfMonth);

        if (loggedInUserId == 0)
        {
            pendingLogin.putExtra(CUSTOMER_ID_EXTRA, loggedInUserId);
            startActivity(pendingLogin);

            finish();
        }
        else
        {
            navigateToLogin();
        }
    }

    /**
     * Starts the login activity , expects a result on onActivityResult
     */
    @Override
    public void navigateToLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(REDIRECT_TO_SEARCH_EXTRA, true);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    /**
     * Called when we get a result from the login activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_LOGIN)
        {
            if (resultCode == QuickRepairApplication.RESULT_OK)
            {
                loggedInUserId = data.getIntExtra(CUSTOMER_ID_EXTRA, 0);
                presenter.setLoggedInUser(loggedInUserId);

                pendingLogin.putExtra(CUSTOMER_ID_EXTRA, loggedInUserId);
                startActivity(pendingLogin);

                finish();
            }
        }
    }


    private class SpinnerEntry
    {
        int id;
        String value;

        public SpinnerEntry(int id, String value)
        {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return value;
        }

        public int getId()
        {
            return id;
        }
    }
}
