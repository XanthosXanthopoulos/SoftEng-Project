package com.example.quickrepair.view.SearchTechnicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quickrepair.R;

import java.util.List;

public class SearchTechniciansActivity extends AppCompatActivity {

    SearchTechniciansViewModel viewModel;
    SearchTechniciansPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_technicians);
    }
}
