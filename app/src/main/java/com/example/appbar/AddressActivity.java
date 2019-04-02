package com.example.appbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddressActivity extends AppCompatActivity {

    private Spinner mCountriesSpinner;
    private Spinner mCitiesSpinner;
    private Spinner mHouseNumberSpinner;
    private Button mShowAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initViews();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void initViews() {
        mCountriesSpinner = findViewById(R.id.countriesSpinner);
        mCitiesSpinner = findViewById(R.id.citiesSpinner);
        mHouseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        mShowAddressBtn = findViewById(R.id.showAddress);
        initSpinnerCountries();
        initHouseNumbersSpinner();

        mShowAddressBtn.setOnClickListener(showAddress);
    }

    View.OnClickListener showAddress = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(AddressActivity.this
                    ,mCountriesSpinner.getSelectedItem().toString()
                            + " "
                            + mCitiesSpinner.getSelectedItem().toString()
                            + " "
                            + mHouseNumberSpinner.getSelectedItem().toString()
                    ,Toast.LENGTH_LONG)
                    .show();
        }
    };

    private void initHouseNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1; i <= 50; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        houseNumbers);
        mHouseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(
                this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountriesSpinner.setAdapter(adapterCountries);

        mCountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities,
                        android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCitiesSpinner.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context packageContext = this;

        int id = item.getItemId();

        if (id == R.id.action_open_notes) {
            Intent intentNotes = new Intent(packageContext, EditTextActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_calendar) {
            Intent intentNotes = new Intent(packageContext, CalendarActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_payment) {
            Intent intentNotes = new Intent(packageContext, PaymentActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
