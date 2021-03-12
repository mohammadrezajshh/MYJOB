package com.karkardanand.project;

import android.content.Intent;

public class ProfailMoudel {

    private String profilePic;
    private String id;
    private String caption;
    private String photo;


    public String getProfilePic() { return profilePic; }

    public void setProfilePic(String value) { this.profilePic = value; }


    public String getID() { return id; }

    public void setID(String value) { this.id = value; }


    public String getCaption() { return caption; }

    public void setCaption(String value) { this.caption = value; }


    public String getPhoto() { return photo; }

    public void setPhoto(String value) { this.photo = value; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfailMoudel{" +
                "profilePic='" + profilePic + '\'' +
                ", id='" + id + '\'' +
                ", caption='" + caption + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}