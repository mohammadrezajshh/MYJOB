package com.karkardanand.project;

import android.content.Intent;

public class ProfailMoudel {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




    public ProfailMoudel(String photo, String id) {
        this.photo = photo;
        this.id= id;

    }
    public String id() {
        return id;
    }
    public String photo() {
        return photo;
    }


    @Override
    public String toString() {
        return "ProfailMoudel{" +
                "id='" + id + '\'' +
                ", photo=" + photo +
                '}';
    }


}
