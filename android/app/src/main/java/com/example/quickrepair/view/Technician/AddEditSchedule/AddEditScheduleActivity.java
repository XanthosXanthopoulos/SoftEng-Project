package com.example.quickrepair.view.Technician.AddEditSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.quickrepair.R;
import com.example.quickrepair.view.Technician.AddEditArea.AddEditAreaActivity;
import com.example.quickrepair.view.Technician.RepairRequests.TechnicianRepairRequestsActivity;

import static com.example.quickrepair.QuickRepairApplication.TECHNICIAN_ID_EXTRA;

public class AddEditScheduleActivity extends AppCompatActivity implements AddEditScheduleView
{
    AddEditScheduleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_schedule);

        viewModel = new ViewModelProvider(this).get(AddEditScheduleViewModel.class);
        final AddEditSchedulePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        presenter.setTechnician(getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
        presenter.setUpDataSource();

        findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.submit(generateSchedule());
            }
        });

        findViewById(R.id.ScheduleNext).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToArea();
            }
        });
    }

    @Override
    public void setDay(int day, int start, int end)
    {
        LinearLayout layout = findViewById(getResources().getIdentifier("day" + day, "id", getPackageName()));

        for (int i = 0; i < 3; ++i)
        {
            if (layout.getChildAt(i) instanceof EditText)
            {
                EditText text = (EditText) layout.getChildAt(i);

                if (text.getHint().equals("Start"))
                {
                    text.setText(String.valueOf(start));
                }
                else
                {
                    text.setText(String.valueOf(end));
                }
            }
        }
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    private Integer[][] generateSchedule()
    {
        Integer[][] schedule = new Integer[7][2];

        for (int i = 0; i < 7; ++i)
        {
            LinearLayout layout = findViewById(getResources().getIdentifier("day" + i, "id", getPackageName()));

            for (int j = 0; j < 3; ++j)
            {
                if (layout.getChildAt(j) instanceof EditText)
                {
                    EditText text = (EditText) layout.getChildAt(j);

                    if (text.getHint().equals("Start"))
                    {
                        schedule[i][0] = Integer.parseInt(text.getText().toString());
                    }
                    else
                    {
                        schedule[i][1] = Integer.parseInt(text.getText().toString());
                    }
                }
            }
        }

        return schedule;
    }

    public void goToArea()
    {
        Intent intent = new Intent(this, AddEditAreaActivity.class);
        intent.putExtra(TECHNICIAN_ID_EXTRA, getIntent().getIntExtra(TECHNICIAN_ID_EXTRA, 0));
        startActivity(intent);

        finish();
    }
}
