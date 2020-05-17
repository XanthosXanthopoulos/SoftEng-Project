package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickrepair.R;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.util.Utilities;
import com.example.quickrepair.view.Technician.RepairRequests.TechnicianRepairRequestsActivity;
import com.example.quickrepair.view.Technician.RepairRequests.TechnicianRepairRequestsPresenter;

import java.util.Calendar;

public class TechnicianUnconfirmedRepairRequestActivity extends AppCompatActivity implements TechnicianUnconfirmedRepairRequestView {

    public static final String REPAIR_REQUEST_ID_EXTRA = "repair_request_id";
    public static final String TECHNICIAN_ID_EXTRA = "technician_id";

    private static int repairRequestID = 0;
    private static int technicianID = 0;
    private TechnicianUnconfirmedRepairRequestViewModel technicianRepairRequestsViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technician_unfconfirmed_repair_request);

        Intent intent = getIntent();
        repairRequestID = intent.getIntExtra(REPAIR_REQUEST_ID_EXTRA, 0);
        technicianID = intent.getIntExtra(TECHNICIAN_ID_EXTRA, 0);

        technicianRepairRequestsViewModel = new ViewModelProvider(this).get(TechnicianUnconfirmedRepairRequestViewModel.class);

        final TechnicianUnconfirmedRepairRequestPresenter presenter = technicianRepairRequestsViewModel.getPresenter();
        presenter.setView(this);

        RepairRequest repairRequest = presenter.searchRepairRequestData(repairRequestID);

        //set ui
         TextView job = findViewById(R.id.job);
         job.setText(repairRequest.getJob().getJobType().getName());

        TextView consumer = findViewById(R.id.consumer);
        String fromString = getResources().getString(R.string.from);
        consumer.setText(fromString + "\n"+repairRequest.getCustomer().getUsername());

        TextView address = findViewById(R.id.address);
        String addressString = getResources().getString(R.string.address);
        address.setText(addressString + "\n" +repairRequest.getAddress().toString());

        TextView comments = findViewById(R.id.comments);
        String commentsString= getResources().getString(R.string.comments);
        comments.setText(commentsString + "\n" + repairRequest.getCommentsFromCustomer());

        TextView conductionDate = findViewById(R.id.conduction_date);
        String conductionDateString = getResources().getString(R.string.date);
        conductionDate.setText(conductionDateString + "\n" + Utilities.getToString(repairRequest.getConductionDate()));

        Button buttonReject = findViewById(R.id.reject);
        buttonReject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.setReject();
            }
        });

        Button buttonConfirm = findViewById(R.id.confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.duration);
                String input = editText.getText().toString();
                presenter.setConfirm(input);
            }
        });
    }


    @Override
    public void reject() {
        Intent intent = new Intent(this, TechnicianRepairRequestsActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
        this.startActivity(intent);
        // close activity
        finish();
    }

    @Override
    public void confirm() {
        Intent intent = new Intent(this, TechnicianRepairRequestsActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, technicianID);
        this.startActivity(intent);
        // close activity
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
