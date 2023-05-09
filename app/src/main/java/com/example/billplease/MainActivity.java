package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText etAmnt;
    EditText etPax;
    EditText etDisc;
    Button btnSplit;
    Button btnReset;
    TextView tvBill;
    TextView tvPay;
    TextView tvError;
    ToggleButton tgSvs;
    ToggleButton tgGst;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDisc = findViewById(R.id.discInput);
        etAmnt = findViewById(R.id.amountInput);
        etPax = findViewById(R.id.paxInput);
        tgSvs = findViewById(R.id.svs);
        tgGst = findViewById(R.id.gst);
        btnSplit = findViewById(R.id.split);
        btnReset = findViewById(R.id.reset);
        tvBill = findViewById(R.id.billInput);
        tvPay = findViewById(R.id.payInput);
        tvError = findViewById(R.id.errorMsg);
        rg = findViewById(R.id.payment);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmnt.getText().toString().trim().length() != 0 && etPax.getText().toString().trim().length() != 0
                && rg.getCheckedRadioButtonId() != -1 ) {
                    double amount = Double.parseDouble(etAmnt.getText().toString());
                    double svs = 1;
                    double gst = 1;

                    if (tgSvs.isChecked()) {
                        svs = 0.1;
                    }
                    if (tgGst.isChecked()) {
                        gst = 0.07;
                    }
                    int pax = Integer.parseInt(etPax.getText().toString());
                    double disc = Integer.parseInt(etDisc.getText().toString());

                    disc = 1 - disc / 100;
                    double extra = amount * svs * gst;
                    double amntCharge = amount + extra;
                    double total = amntCharge * disc;
                    String outputTotal = String.format("$%.2f", total);


                    double splitAmount = total / pax;
                    String payment = " in Cash";
                    int mode = rg.getCheckedRadioButtonId();
                    if (mode == R.id.paynow) {
                        payment = " via PayNow to 91234567";
                    }
                    String outputPay = String.format("$%.2f %s", splitAmount, payment);

                    tvBill.setText(outputTotal);
                    tvPay.setText(outputPay);
                }else{
                    tvError.setText("Options not filled!");
            }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmnt.setText("");
                etPax.setText("");
                etDisc.setText("");
                rg.clearCheck();
                tgGst.setChecked(false);
                tgSvs.setChecked(false);

            }
        });

            }
    }






