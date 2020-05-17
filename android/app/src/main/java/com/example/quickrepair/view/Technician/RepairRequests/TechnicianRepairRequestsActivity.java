package com.example.quickrepair.view.Technician.RepairRequests;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.quickrepair.R;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.view.Technician.ShowCompletedRepairRequest.CompletedRepairRequestPage;
import com.example.quickrepair.view.Technician.ShowConfirmedRepairRequest.ConfirmedRepairRequestPage;
import com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest.UnconfirmedRepairRequestPage;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class TechnicianRepairRequestsActivity extends AppCompatActivity implements TechnicianRepairRequestsView
{

    private static int technicianID = 1;
    private static TechnicianRepairRequestsViewModel technicianRepairRequestsViewModel;

    public static final String REPAIR_REQUEST_ID_EXTRA = "repair_request_id";
    public static final String TECHNICIAN_ID_EXTRA = "technician_id";

    boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technician_repair_requests);

        if (initialized == false)
        {
            new MemoryInitializer().prepareData();
            initialized = true;
        }

        //get Technician id
        Intent intent = getIntent();
        technicianID = intent.getIntExtra("TECHNICIAN_ID_EXTRA", 0);

        technicianID = 1;
        technicianRepairRequestsViewModel = new ViewModelProvider(this).get(TechnicianRepairRequestsViewModel.class);

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
        Intent intent = new Intent(this, UnconfirmedRepairRequestPage.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
        // close activity
        finish();
    }

    @Override
    public void returnRepairRequestUnconfirmed(int repairRequestUid)
    {
        // return result to calling Activity
        Intent intent = new Intent(this, UnconfirmedRepairRequestPage.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        // close activity
        finish();
    }

    @Override
    public void returnRepairRequestConfirmed(int repairRequestUid)
    {
        // return result to calling Activity
        Intent intent = new Intent(this, ConfirmedRepairRequestPage.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        // close activity
        finish();
    }

    @Override
    public void returnRepairRequestCompleted(int repairRequestUid)
    {
        // return result to calling Activity
        Intent intent = new Intent(this, CompletedRepairRequestPage.class);
        intent.putExtra(REPAIR_REQUEST_ID_EXTRA, repairRequestUid);
        // close activity
        finish();
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
