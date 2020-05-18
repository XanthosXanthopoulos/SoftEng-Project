package com.example.quickrepair.view.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickrepair.R;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.view.Customer.RegisterCustomer.CustomerRegisterActivity;
import com.example.quickrepair.view.SearchTechnicians.SearchTechniciansActivity;
import com.example.quickrepair.view.Technician.RegisterTechnician.TechnicianRegisterActivity;
import com.example.quickrepair.view.User.LoginUser.LoginActivity;

public class HomePageActivity extends AppCompatActivity implements HomePageView {

    private HomePageViewModel viewModel;

    public static final int REQUEST_CODE_TECHNICIAN_SEARCH = 1;
    public static final int REQUEST_CODE_LOGIN = 2;
    public static final int REQUEST_CODE_REGISTER = 3;

    boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        if(initialized == false){
            new MemoryInitializer().prepareData();
            initialized = true;
        }

        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        final HomePagePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        //Buttons
        Button search = findViewById(R.id.search);
        Button logInPage = findViewById(R.id.login);
        Button registerTechnicianPage = findViewById(R.id.registerCustomer);
        Button registerCustomerPage = findViewById(R.id.registerTeachnician);

        //Button's listeners
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSearchSelected();
            }
        });

        logInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogInSelected();
            }
        });

        registerTechnicianPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterAsTechnicianSelected();
            }
        });

        registerCustomerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterAsCustomerSelected();
            }
        });

    }

    /**
     * Όταν πραγματοποιείται click στο κουμπι για login , ο χρηστης μεταφερεται στο κατάλληλο
     * activity
     */
    @Override
    public void login() {
        //navigate to login page
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
        // close activity
        finish();
    }

    /**
     * Όταν πραγματοποιείται click στο κουμπι για signup , ο χρηστης μεταφερεται στο κατάλληλο
     * activity
     */
    @Override
    public void registerAsTechnician() {
        //navigate to register page
        Intent intent = new Intent(this, TechnicianRegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
        // close activity
        finish();
    }

    /**
     * Όταν πραγματοποιείται click στο κουμπι για signup , ο χρηστης μεταφερεται στο κατάλληλο
     * activity
     */
    @Override
    public void registerAsCustomer() {
        //navigate to register page
        Intent intent = new Intent(this, CustomerRegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
        // close activity
        finish();
    }

    /**
     * Όταν πραγματοποιείται click στο κουμπι για αναζήτηση τεχνικού μεταφερόμαστε στο κατάλληλο
     * activity
     */
    @Override
    public void searchTechnicians() {
        //navigate to search page
        Intent intent = new Intent(this, SearchTechniciansActivity.class);
        startActivityForResult(intent, REQUEST_CODE_TECHNICIAN_SEARCH);
        // close activity
        finish();
    }

}
