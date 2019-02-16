package com.example.pen.realworld.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateVo {
    private Date date;

    public DateVo(Date currDate) {
        this.date = currDate;
    }

    public Date getCurrDate() {
        return date;
    }

    public void setCurrDate(Date currDate) {
        this.date = currDate;
    }

    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시");
        return simpleDateFormat.format(date);
    }
}
