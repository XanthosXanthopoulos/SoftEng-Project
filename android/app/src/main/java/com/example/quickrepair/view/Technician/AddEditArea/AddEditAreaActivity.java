package com.example.quickrepair.view.Technician.AddEditArea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.quickrepair.R;
import com.example.quickrepair.view.Technician.AddEditJob.AddEditJobActivity;

import java.util.List;

import static com.example.quickrepair.QuickRepairApplication.TECHNICIAN_ID_EXTRA;

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

        presenter.setTechnician(getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
        presenter.setUpDataSource();

        findViewById(R.id.AddAreaButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.addArea(((Spinner)findViewById(R.id.AreaSpinner)).getSelectedItemPosition());
            }
        });

        findViewById(R.id.NextButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToEditJob();
            }
        });
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

    public void goToEditJob()
    {
        Intent intent = new Intent(this, AddEditJobActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }
}
