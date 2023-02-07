package com.sazkuyo.ultrawallpaper2.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class visitor {
    private int id;
    private String adr;
    @DateTimeFormat(pattern = "yy-MM-dd HH-mm-ss")
    private Date visit_time;

    public visitor() {
    }

    public visitor(int id, String adr, Date visit_time) {
        this.id = id;
        this.adr = adr;
        this.visit_time = visit_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }
}
