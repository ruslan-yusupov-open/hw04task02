package com.example.appbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initViews();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void initViews() {
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);
        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);

        mBtnOk.setOnClickListener((view) -> {
            StringBuilder text = new StringBuilder();
            text.append("оплата ");
            text.append(mInputMoney.getText().toString());
            text.append(" рублей, сообщение '");
            text.append(mInputInfo.getText().toString());
            text.append("', вариант оплаты ");
            if (mBankCardChkBx.isChecked())
                text.append("банковская карта");
            if (mMobilePhoneChkBx.isChecked())
                text.append("мобильный телефон");
            if (mCashAddressChkBx.isChecked())
                text.append("наличные");

            Toast.makeText(
                    PaymentActivity.this,
                    text.toString(),
                    Toast.LENGTH_LONG).show();
        });
    }

    private void resetCheckBoxes() {
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        switch (compoundButton.getId()) {
                            case R.id.bankCardChkBx:
                                resetCheckBoxes();
                                mBankCardChkBx.setChecked(true);
                                mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                                break;
                            case R.id.mobilePhoneChkBx:
                                resetCheckBoxes();
                                mMobilePhoneChkBx.setChecked(true);
                                mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                                break;
                            case R.id.cashAddressChkBx:
                                resetCheckBoxes();
                                mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                                mCashAddressChkBx.setChecked(true);
                                break;
                            default:
                        }
                    }
                }
            };

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

        return super.onOptionsItemSelected(item);
    }
}
