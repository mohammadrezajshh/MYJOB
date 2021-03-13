package com.karkardanand.project;

import android.content.Intent;

public class ProfailMoudel {

    private long id;
    private String photo;



    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public ProfailMoudel(Long id, String photo) {
        this.id = id;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "postsList{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String value) { this.photo = value; }



}
