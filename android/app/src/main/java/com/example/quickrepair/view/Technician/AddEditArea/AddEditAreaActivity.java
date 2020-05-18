package com.example.quickrepair.view.Technician.AddEditArea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.quickrepair.R;
import com.example.quickrepair.view.Technician.RegisterTechnician.TechnicianRegisterPresenter;
import com.example.quickrepair.view.Technician.RegisterTechnician.TechnicianRegisterViewModel;

import java.util.List;

public class AddEditAreaActivity extends AppCompatActivity implements AddEditAreaView
{
    AddEditAreaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_area);

        viewModel = new ViewModelProvider(this).get(AddEditAreaViewModel.class);
        final AddEditAreaPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        presenter.setTechnician(getIntent().getIntExtra("TECHNICIAN_ID_EXTRA", 0));
        presenter.setUpDataSource();
    }

    @Override
    public void setAreaList(List<String> areaList, String defaultName)
    {
        areaList.add(0, defaultName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner)findViewById(R.id.AreaSpinner)).setAdapter(adapter);
    }

    @Override
    public void setSelectedArea(List<String> selectedArea)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedArea);

        ((ListView)findViewById(R.id.AreaList)).setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String title, String message)
    {

    }
}
