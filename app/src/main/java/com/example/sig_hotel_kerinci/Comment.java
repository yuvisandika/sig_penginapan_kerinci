package com.example.sig_hotel_kerinci;

import com.google.firebase.database.ServerValue;


public class Comment {

    private String content,uid,uname,rate;
    private Object timestamp;


    public Comment(String content, String uid, String uname, String rate) {
        this.content = content;
        this.uid = uid;
        this.uname = uname;
        this.timestamp = ServerValue.TIMESTAMP;
        this.rate = rate;

    }

    public Comment() {
    }

    public Comment(String content, String uid, String uname, Object timestamp, String rate) {
        this.content = content;
        this.uid = uid;
        this.uname = uname;
        this.timestamp = timestamp;
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

