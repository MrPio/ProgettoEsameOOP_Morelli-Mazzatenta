package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable {
    private String message;
    private LocalTime time;
    private boolean read;

    public Message(String message) {
        this.message = message;
        this.time = LocalTime.now();
        read=false;
    }

    public Message(String message, LocalTime time) {
        this.message = message;
        this.time = time;
        read=false;
    }

    public String getMessage() {
        return message;
    }

    public LocalTime getTime() {
        return time;
    }
    public void read(){
        read=true;
    }
}
