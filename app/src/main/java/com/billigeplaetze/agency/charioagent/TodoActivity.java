package com.billigeplaetze.agency.charioagent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @BindView(R.id.money_received)
    TextView moneyReceived;
    @BindView(R.id.orderCheckbox)
    CheckBox orderCheckbox;
    @BindView(R.id.accept_order)
    Button acceptOrder;

    @BindView(R.id.moneytaken)
    TextView moneytaken;
    @BindView(R.id.orderCheckbox2)
    CheckBox checkBox_two;

    private String transactionId;
    private String description;
    private double money;
    private String Type;
    private Context lul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);

        if(AppSettings.getAgentName().equalsIgnoreCase("A")) {
            checkBox_two.setVisibility(View.VISIBLE);
        }

        final Bundle transactionBundle = this.getIntent().getBundleExtra("extra");
        description = transactionBundle.getString("description");
        money = transactionBundle.getDouble("amount");
        transactionId = transactionBundle.getString("transactionId");
        Type = transactionBundle.getString("Type");

        moneyReceived.setText(money + " €");
        orderCheckbox.setText(description);
        lul = this;
        orderCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(orderCheckbox.isChecked()){
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                    if(AppSettings.getAgentName().equalsIgnoreCase("A")){
                        moneytaken.setText("-200 €");
                    }
                    if(AppSettings.getAgentName().equalsIgnoreCase("B")) {
                        moneytaken.setText("- 1x Goat");
                    }
                }
            }
        });


    }

    @OnClick(R.id.accept_order)
    public void onAcceptOrderClicked(){


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            new UploadImageAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, imageBitmap);
        }
    }

    private class UploadImageAsync extends AsyncTask<Bitmap, Void, Void> {

        @Override
        protected Void doInBackground(Bitmap... bitmaps) {
            Bitmap dickPic = bitmaps[0];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            dickPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteDick = stream.toByteArray();
            String dickString = Base64.encodeToString(byteDick, Base64.NO_WRAP);
            Transaction transaction = new Transaction();
            TransactionId id = new TransactionId();
            id.donationCode = transactionId;
            id.agentId = AppSettings.getAgentName();
            transaction.setTransactionId(id);
            transaction.setType(Type);
            transaction.setTaskDescription(description);
            transaction.setDonationAmount(money);
            transaction.setState("DONE");
            transaction.setPhoto(dickString);
            try {
                Gson gson = new Gson();
                String json = gson.toJson(transaction);
                URL url = new URL(AppSettings.dickPickAddress);

                /**
                URL url = new URL(AppSettings.dickPickAddress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type","application/json");
                connection.connect();
                DataOutputStream printout = new DataOutputStream(connection.getOutputStream ());
                printout.writeBytes(URLEncoder.encode(json,"UTF-8"));
                printout.flush ();
                printout.close ();
                // Create the data
                if (connection.getResponseCode() < 300) {
                    System.out.println("Dick Pick sehr gross");
                } else {
                    System.out.println("Oh schade no dickpick");
                }
                 **/

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject jsonParam = new JSONObject(json);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                System.out.println(String.valueOf(conn.getResponseCode()));

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
