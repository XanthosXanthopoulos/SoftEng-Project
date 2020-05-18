package com.example.quickrepair.view.Technician.RepairRequests;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.quickrepair.R;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.view.Technician.ShowCompletedRepairRequest.TechnicianCompletedRepairRequestActivity;
import com.example.quickrepair.view.Technician.ShowConfirmedRepairRequest.TechnicianConfirmedRepairRequestActivity;
import com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest.TechnicianUnconfirmedRepairRequestActivity;

import com.google.android.material.tabs.TabLayout;

public class TechnicianRepairRequestsActivity extends AppCompatActivity implements TechnicianRepairRequestsView
{

    private static int technicianID ;
    private static TechnicianRepairRequestsViewModel technicianRepairRequestsViewModel;

    public static final String REPAIR_REQUEST_ID_EXTRA = "repair_request_id";
    public static final String TECHNICIAN_ID_EXTRA = "technician_id";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technician_repair_requests);

        //get Technician id
        Intent intent = getIntent();
        technicianID = intent.getIntExtra("TECHNICIAN_ID_EXTRA", 0);


        technicianRepairRequestsViewModel = new ViewModelProvider(this).get(TechnicianRepairRequestsViewModel.class);
        technicianRepairRequestsViewModel.getPresenter().setView(this);

        ViewRepairRequestsPagerAdapter sectionsPagerAdapter = new ViewRepairRequestsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.technician_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.edit:
                technicianRepairRequestsViewModel.getPresenter().onEditDataPage();
               return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


    //TODO:go to edit page
    @Override
    public void editData() {
        // return result to calling Activity
        Intent intent = new Intent(this, TechnicianUnconfirmedRepairRequestActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void returnRepairRequestUnconfirmed(int repairRequestUid) {
        // return result to calling Activity
        Intent intent = new Intent(this, TechnicianUnconfirmedRepairRequestActivity.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
        this.startActivity(intent);

    }

    @Override
    public void returnRepairRequestConfirmed(int repairRequestUid)
    {
        Intent intent = new Intent(this, TechnicianConfirmedRepairRequestActivity.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
        this.startActivity(intent);
    }

    @Override
    public void returnRepairRequestCompleted(int repairRequestUid)
    {
        Intent intent = new Intent(this, TechnicianCompletedRepairRequestActivity.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        this.startActivity(intent);
    }


    public TechnicianRepairRequestsViewModel getViewModel()
    {
        return technicianRepairRequestsViewModel;
    }

    public static int getTechnicianID()
    {
        return technicianID;
    }

}
