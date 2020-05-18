package com.example.quickrepair.view.SearchTechnicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quickrepair.R;
import com.example.quickrepair.view.User.LoginUser.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchTechniciansActivity extends AppCompatActivity implements SearchTechniciansView{

    SearchTechniciansViewModel viewModel;
    SearchTechniciansPresenter presenter;

    Spinner specialtySpinner;
    Spinner jobTypeSpinner;
    Spinner areaSpinner;

    ArrayAdapter<SpinnerEntry> specialtyAdapter;
    ArrayAdapter<SpinnerEntry> jobTypeAdapter;
    ArrayAdapter<String> areaAdapter;

    TechnicianAdapter technicianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_technicians);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        //TODO get loggedin id somehow


        viewModel = new ViewModelProvider(this).get(SearchTechniciansViewModel.class);
        presenter = viewModel.getPresenter();
        presenter.setView(this);

        int id = intent.getIntExtra("id" , -1);
        if(id >= 0){
            presenter.setLoggedInUser(id);
        }
        //TODO Remove this
        else{
            id = 1;
        }

        //Setting spinner adapters
        specialtySpinner = findViewById(R.id.specialty_spinner);
        jobTypeSpinner = findViewById(R.id.job_type_spinner);
        areaSpinner = findViewById(R.id.area_spinner);

        specialtyAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);
        jobTypeAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);
        areaAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);


        specialtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntry entry = (SpinnerEntry) parent.getAdapter().getItem(position);
                System.out.println("Filtering specialty");
                presenter.selectSpecialty(entry.id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        jobTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntry entry = (SpinnerEntry) parent.getAdapter().getItem(position);
                System.out.println("Filtering jobtype");
                presenter.selectJobType(entry.id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Filtering area");
                presenter.filterArea(parent.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        specialtySpinner.setAdapter(specialtyAdapter);
        jobTypeSpinner.setAdapter(jobTypeAdapter);
        areaSpinner.setAdapter(areaAdapter);
        //Setting technicianListAdapters
        ListView techniciansList = findViewById(R.id.technician_list);
        technicianAdapter = new TechnicianAdapter(this);
        techniciansList.setAdapter(technicianAdapter);
        presenter.onStart();

    }

    @Override
    public void setJobTypeSpinnerEnabled(boolean b) {
        findViewById(R.id.job_type_spinner).setEnabled(b);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this , errorMessage , Toast.LENGTH_SHORT);
    }

    @Override
    public void setSpecialtiesSource(List<Integer> specialtyIds, List<String> specialtyNames) {
        List<SpinnerEntry> list = new ArrayList<>();
        for(int i = 0 ; i < specialtyIds.size() ; i++){
            list.add(new SpinnerEntry(specialtyIds.get(i) , specialtyNames.get(i)));
        }
        specialtyAdapter = new ArrayAdapter<>(this
                , R.layout.support_simple_spinner_dropdown_item,list);
        specialtySpinner.setAdapter(specialtyAdapter);

    }

    @Override
    public void setJobTypesSource(List<Integer> jobTypeIds, List<String> jobTypeNames) {
        List<SpinnerEntry> list = new ArrayList<>();
        for(int i = 0 ; i < jobTypeIds.size() ; i++){
            list.add(new SpinnerEntry(jobTypeIds.get(i) , jobTypeNames.get(i)));
        }
        jobTypeAdapter = new ArrayAdapter<>(this
                , R.layout.support_simple_spinner_dropdown_item,list);
        jobTypeSpinner.setAdapter(jobTypeAdapter);
    }

    @Override
    public void setAreasSource(List<String> areas) {
        areaAdapter = new ArrayAdapter<>(this ,
                R.layout.support_simple_spinner_dropdown_item , areas);
        areaSpinner.setAdapter(areaAdapter);
    }

    @Override
    public void populateTechnicianList(List<Integer> technicianIds, List<String> technicianNames, List<Double> averageRatings, List<Double> prices) {
        Log.e("x" , "found" + technicianIds.size() + " technicians");
        technicianAdapter.setSource(technicianIds , technicianNames , averageRatings , prices);
    }

    @Override
    public void navigateToRequestRepair(int technicianId, int jobTypeId) {
        //TODO Fix this once requestrepairActivity is complete
        //navigate to login page
        //Intent intent = new Intent(this, RequestRepairActivity.class);
        //startActivityForResult(intent, 0);
        // close activity
        //finish();
    }

    @Override
    public void navigateToLogin() {
        //navigate to login page
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 0);
        // close activity
        finish();
    }
    private  class SpinnerEntry {
        int id;
        String value;
        public SpinnerEntry(int id , String value){
            this.id = id;
            this.value = value;
        }
        @Override
        public String toString(){
            return value;
        }
        public int getId(){
            return id;
        }
    }
}
