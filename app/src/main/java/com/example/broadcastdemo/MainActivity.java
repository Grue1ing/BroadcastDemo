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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String BARCODE_INTENT_KEY = "barcode_string";
    private static final String ACTION = "android.intent.ACTION_DECODE_DATA";

    private BarcodeAdapter barcodeAdapter;
    BroadcastReceiver broadcastReceiver;

    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String value = intent.getStringExtra(BARCODE_INTENT_KEY);
                if (!value.isEmpty()) {
                    Barcode barcode = new Barcode(value);
                    barcodeAdapter.setItems(barcode);
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
        barcodeAdapter = new BarcodeAdapter();
        barcodeRecyclerView.setAdapter(barcodeAdapter);
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
        barcodeAdapter.clearItems();
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
