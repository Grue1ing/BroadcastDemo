package com.example.broadcastdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class BarcodeListAdapter extends RecyclerView.Adapter<BarcodeListAdapter.BarcodeViewHolder> {
    private List<BarcodeItem> barcodeItemList = new LinkedList<>();

    @NonNull
    @Override
    public BarcodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.barcode_view, parent, false);
        return new BarcodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcodeViewHolder holder, int position) {
        holder.bind(barcodeItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return barcodeItemList.size();
    }

    // function for addition barcode item
    public void setItems(BarcodeItem barcodeItem) {
        barcodeItemList.add(barcodeItem);
        notifyDataSetChanged();
    }

    // function for delete barcode item
    public void clearItems() {
        barcodeItemList.clear();
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

        void bind(BarcodeItem barcodeItem) {
            barcodeValue.setText(barcodeItem.getValue());
            barcodeDate.setText(barcodeItem.getDate());
            barcodeTime.setText(barcodeItem.getTime());
        }
    }
}
