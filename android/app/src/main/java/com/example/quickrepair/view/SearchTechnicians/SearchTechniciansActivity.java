package com.example.quickrepair.view.SearchTechnicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quickrepair.R;

import java.util.List;

public class SearchTechniciansActivity extends AppCompatActivity  implements SearchTechniciansView{

    SearchTechniciansViewModel viewModel;
    SearchTechniciansPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_technicians);
        //TODO Initialize DAOS
        presenter = new SearchTechniciansPresenter(this , null ,null , null ,null);

        presenter.onStart();

        //TODO ViewModel
        //viewModel = new ViewModelProvider(this).get(SearchTechniciansViewModel.class);

    }

    @Override
    public void showJobTypeFromSpecialty(List<String> jobTypeNames) {

    }

    @Override
    public void populateTechnicianList(List<Integer> technicianIds, List<String> technicianNames, List<Double> averageRatings, List<Double> prices) {

    }

    @Override
    public void showSpecialtyDialog(List<Integer> specialtyIds, List<String> specialtyNames) {
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                specialtyNames );

        list.setAdapter(arrayAdapter);

    }

    @Override
    public void showJobTypeDialog(List<Integer> jobTypeIds, List<String> jobTypeNames) {

    }

    @Override
    public void navigateToRequestRepair() {

    }

    /**
     *  Class that is used to populate the list of specialties
     */
    private class SpecialtyEntry{
        int id;
        String name;

        @Override
        public String toString() {
            return "SpecialtyEntry{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
