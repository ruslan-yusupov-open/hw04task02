package com.example.appbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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

        if (id == R.id.action_main) {
            Intent intentNotes = new Intent(packageContext, MainActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_notes) {
            Intent intentNotes = new Intent(packageContext, EditTextActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_address) {
            Intent intentNotes = new Intent(packageContext, AddressActivity.class);
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
