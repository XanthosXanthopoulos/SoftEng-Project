package com.example.quickrepair.view.Technician.AddEditArea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quickrepair.R;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.view.Technician.AddEditJob.AddEditJobActivity;
import com.example.quickrepair.view.Technician.RegisterTechnician.TechnicianRegisterActivity;
import com.example.quickrepair.view.Technician.RepairRequests.TechnicianRepairRequestsActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.quickrepair.QuickRepairApplication.TECHNICIAN_ID_EXTRA;

public class AddEditAreaActivity extends AppCompatActivity implements AddEditAreaView
{
    AddEditAreaViewModel viewModel;

    /**
     * Create and initialize the activity.
     *
     * @param savedInstanceState The activity's saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_area);

        viewModel = new ViewModelProvider(this).get(AddEditAreaViewModel.class);
        final AddEditAreaPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        final RecyclerView areaList = findViewById(R.id.AreaList);

        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 1);
        areaList.setLayoutManager(manager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir)
            {
                presenter.removeArea(viewHolder.getAdapterPosition());
                presenter.setUpDataSource();
            }
        });

        itemTouchHelper.attachToRecyclerView(areaList);

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

    /**
     * Populate the list holding all available areas.
     *
     * @param areaList The list of areas.
     * @param defaultName A default area placeholder.
     */
    @Override
    public void setAreaList(List<String> areaList, String defaultName)
    {
        areaList.add(0, defaultName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner)findViewById(R.id.AreaSpinner)).setAdapter(adapter);
    }

    /**
     * Populate the list holding the technician's areas.
     *
     * @param selectedArea The list of the technician's areas.
     */
    @Override
    public void setSelectedArea(List<String> selectedArea)
    {
        RecyclerView areaList = findViewById(R.id.AreaList);
        AreaAdapter adapter = new AreaAdapter(new ArrayList<>(selectedArea));
        areaList.setAdapter(adapter);
    }

    /**
     * Navigate to the AddEditJob page.
     */
    public void goToEditJob()
    {
        Intent intent = new Intent(this, AddEditJobActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
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
        Intent intent = new Intent(this, TechnicianRepairRequestsActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
        startActivity(intent);

        finish();
    }
}
