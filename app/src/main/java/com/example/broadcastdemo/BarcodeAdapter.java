package com.example.broadcastdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.BarcodeViewHolder> {
    private List<Barcode> barcodeList = new LinkedList<>();

    @NonNull
    @Override
    public BarcodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.barcode_view, parent, false);
        return new BarcodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcodeViewHolder holder, int position) {
        holder.bind(barcodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return barcodeList.size();
    }

    public void setItems(Barcode barcode) {
        barcodeList.add(barcode);
        notifyDataSetChanged();
    }

    public void clearItems() {
        barcodeList.clear();
        notifyDataSetChanged();
    }

    class BarcodeViewHolder extends RecyclerView.ViewHolder {
        private TextView barcodeValue;
        private TextView barcodeDate;
        private TextView barcodeTime;

        BarcodeViewHolder(View itemView) {
            super(itemView);
            barcodeValue = itemView.findViewById(R.id.barcode_value);
            barcodeDate = itemView.findViewById(R.id.barcode_date);
            barcodeTime = itemView.findViewById(R.id.barcode_time);
        }

        void bind(Barcode barcode) {
            barcodeValue.setText(barcode.getValue());
            barcodeDate.setText(barcode.getDate());
            barcodeTime.setText(barcode.getTime());
        }
    }
}
