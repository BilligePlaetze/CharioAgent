package com.billigeplaetze.agency.charioagent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoActivity extends AppCompatActivity {
    @BindView(R.id.money_received)
    TextView moneyReceived;
    @BindView(R.id.orderCheckbox)
    CheckBox orderCheckbox;

    private String description;
    private double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);

        Bundle transactionBundle = this.getIntent().getBundleExtra("extra");
        description = transactionBundle.getString("description");
        money = transactionBundle.getDouble("amount");

        moneyReceived.setText(money + " €");
        orderCheckbox.setText(description);


    }
}
