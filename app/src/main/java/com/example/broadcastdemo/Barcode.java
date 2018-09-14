package com.example.broadcastdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

class Barcode {
    private String value;
    private String date;
    private String time;

    Barcode(String value) {
        this.value = value;

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat formatForTimeNow = new SimpleDateFormat("hh:mm:ss");

        this.date = formatForDateNow.format(dateNow);
        this.time = formatForTimeNow.format(dateNow);
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
