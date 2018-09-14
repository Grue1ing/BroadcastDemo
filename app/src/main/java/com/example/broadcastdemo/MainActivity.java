package com.example.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String BARCODE_INTENT_KEY = "barcode_string";
    private static final String ACTION = "android.intent.ACTION_DECODE_DATA";

    private BarcodeListAdapter barcodeListAdapter;
    BroadcastReceiver broadcastReceiver;

    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat formatForTimeNow = new SimpleDateFormat("hh:mm:ss");

                String value = intent.getStringExtra(BARCODE_INTENT_KEY);
                if (!value.isEmpty()) {
                    BarcodeItem barcodeItem = new BarcodeItem(value, formatForDateNow.format(dateNow),
                            formatForTimeNow.format(dateNow));
                    barcodeListAdapter.setItems(barcodeItem);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        btnAdd = findViewById(R.id.floatingActionButton);
        btnAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        RecyclerView barcodeRecyclerView = findViewById(R.id.barcode_recycler_view);
        barcodeListAdapter = new BarcodeListAdapter();
        barcodeRecyclerView.setAdapter(barcodeListAdapter);
        barcodeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        barcodeRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        barcodeListAdapter.clearItems();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        sendBarcode(generateBarcode());
    }

    private void sendBarcode(String value) {
        Intent intent = new Intent(ACTION);
        intent.putExtra(BARCODE_INTENT_KEY, value);
        sendBroadcast(intent);
    }

    private String generateBarcode() {
        Random random = new Random();
        return "20000000" + random.nextInt(10) + random.nextInt(10) +
                random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
    }
}
