package com.billigeplaetze.agency.charioagent;

import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import kacke.Kackclass;

public class QueueActivity extends AppCompatActivity {
    @BindView(R.id.queue_view)
    RecyclerView queue;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        ButterKnife.bind(this);

        queue.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        queue.setLayoutManager(layoutManager);
        transactions = new ArrayList<Transaction>();
        adapter = new QueueAdapter(transactions, this);
        queue.setAdapter(adapter);

        doNetworking();
    }

    private void doNetworking() {
        final QueueActivity activity = this;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String adresse = AppSettings.getTransactions + "?agentId=" + AppSettings.getAgentName() + "&state=NEW";
                    URL url = new URL(adresse);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    if (connection.getResponseCode() == 200) {
                        InputStream responseBody = connection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        BufferedReader reader = new BufferedReader(responseBodyReader);
                        String line = null;
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }

                        Gson gson = new Gson();

                        Kackclass kacke = gson.fromJson(sb.toString(), Kackclass.class);
                        System.out.println(sb.toString());
                        System.out.println(kacke.getEmbedded().getTransactions().size());
                        for (kacke.Transaction t : kacke.getEmbedded().getTransactions()){
                            Transaction transaction = new Transaction();
                            TransactionId id = new TransactionId();
                            id.agentId = t.getTransactionId().getAgentId();
                            id.donationCode = t.getTransactionId().getDonationCode();
                            transaction.setTransactionId(id);
                            transaction.setDonationAmount(t.getDonationAmount());
                            transaction.setPhoto(t.getPhoto());
                            transaction.setState(t.getState());
                            transaction.setTaskDescription(t.getTaskDescription());
                            transaction.setType(t.getType());
                            transactions.add(transaction);
                        }

                    } else {
                        // Error handling code goes here
                        System.out.println("Feels bad");
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            System.out.println(adapter.getItemCount());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("kacke");
                }
            }
        });

    }
}
