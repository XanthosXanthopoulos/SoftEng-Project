package com.example.quickrepair.view.SearchTechnicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    EditText maxpriceText;

    ArrayAdapter<SpinnerEntry> specialtyAdapter;
    ArrayAdapter<SpinnerEntry> jobTypeAdapter;
    ArrayAdapter<String> areaAdapter;

    TechnicianAdapter technicianAdapter;
    ListView techniciansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_technicians);
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
        presenter.setLoggedInUser(id);
        maxpriceText = findViewById(R.id.max_price_text);

        //Setting spinner adapters
        specialtySpinner = findViewById(R.id.specialty_spinner);
        jobTypeSpinner = findViewById(R.id.job_type_spinner);
        areaSpinner = findViewById(R.id.area_spinner);

        specialtyAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);
        jobTypeAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);
        areaAdapter = new ArrayAdapter<>(this , R.layout.support_simple_spinner_dropdown_item);



        specialtySpinner.setAdapter(specialtyAdapter);
        jobTypeSpinner.setAdapter(jobTypeAdapter);
        areaSpinner.setAdapter(areaAdapter);

        specialtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerEntry entry = (SpinnerEntry) parent.getAdapter().getItem(position);
                presenter.selectSpecialty(entry.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Setting technicianListAdapters
        techniciansList = findViewById(R.id.technician_list);
        technicianAdapter = new TechnicianAdapter(this , R.layout.search_technicians_technician_list_layout);
        techniciansList.setAdapter(technicianAdapter);

        Button b = findViewById(R.id.search_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("w" , "vie button clicked");
                SpinnerEntry entry = (SpinnerEntry) jobTypeSpinner.getSelectedItem();
                int selectedJobTypeId = entry.getId();
                String selectedMaxPrice =  maxpriceText.getText().toString();
                String selectedArea = (String) areaSpinner.getSelectedItem();
                presenter.selectJobType(selectedJobTypeId);
                presenter.filterByMaxPrice(selectedMaxPrice);
                presenter.filterArea(selectedArea);
            }
        });
        techniciansList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e( "fuck", "Clicked on technician id  " + id);
                presenter.onTechnicianClick((int)id);
            }
        });
        presenter.onStart();

    }

    @Override
    public void setJobTypeSpinnerEnabled(boolean b) {
        findViewById(R.id.job_type_spinner).setEnabled(b);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this , errorMessage , Toast.LENGTH_SHORT).show();
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
        System.out.println("Setting specialties source" + jobTypeNames);
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
        Log.e("Pop" , "Populating tech list with " + technicianIds);
        System.out.println("tech size " + technicianIds.size());
        System.out.println("names size " + technicianNames.size());
        System.out.println("avg rat " + averageRatings.size());
        System.out.println("prices z" + prices.size());
        TechnicianAdapter technicianAdapter = new TechnicianAdapter(this, 2);
        technicianAdapter.setSource(technicianIds , technicianNames , averageRatings , prices);
        techniciansList.setAdapter(technicianAdapter);
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
