package com.example.broadcastdemo;


class BarcodeItem {
    private String value;
    private String date;
    private String time;

    BarcodeItem(String value, String date, String time) {
        this.value = value;
        this.date = date;
        this.time = time;
    }

    public String getValue() {
        return this.value;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }
}
