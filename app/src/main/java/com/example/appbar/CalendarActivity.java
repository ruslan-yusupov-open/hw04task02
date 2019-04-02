package com.example.appbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {
    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndtDateCalendar;
    private Button mBtnOK;

    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initViews();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndtDateCalendar = findViewById(R.id.endtDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);

        // Скроем календари при запуске приложения
        mStartDateCalendar.setVisibility(View.GONE);
        mEndtDateCalendar.setVisibility(View.GONE);

        mChooseStartDate.setOnClickListener(view -> {
            mStartDateCalendar.setVisibility(View.VISIBLE);
            mEndtDateCalendar.setVisibility(View.GONE);
        });

        mChooseEndDate.setOnClickListener(view -> {
            mEndtDateCalendar.setVisibility(View.VISIBLE);
            mStartDateCalendar.setVisibility(View.GONE);
        });

        mStartDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            mStartDateTxt = i + "-" + i1 + "-" + i2;
            mChooseStartDate.setText("Дата-время старта задачи: " + mStartDateTxt);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(i, i1, i2);
            mStartDate = gregorianCalendar.getTimeInMillis();
            calendarView.setVisibility(View.GONE);
        });

        mEndtDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            mEndDateTxt = i + "-" + i1 + "-" + i2;
            mChooseEndDate.setText("Дата-время окончания задачи: " + mEndDateTxt);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(i, i1, i2);
            mEndDate = gregorianCalendar.getTimeInMillis();
            calendarView.setVisibility(View.GONE);
        });

        mBtnOK.setOnClickListener(view -> {
            if (mStartDateTxt == null || mEndDateTxt == null || mStartDate > mEndDate) {
                Toast.makeText(CalendarActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                mChooseStartDate.setText("Дата-время старта задачи:");
                mChooseEndDate.setText("Дата-время окончания задачи:");
            } else {
                Toast.makeText(CalendarActivity.this, "старт: " +
                        mStartDateTxt + " окончаниe: " + mEndDateTxt, Toast.LENGTH_LONG).show();
            }
        });
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

        if (id == R.id.action_open_payment) {
            Intent intentNotes = new Intent(packageContext, PaymentActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
