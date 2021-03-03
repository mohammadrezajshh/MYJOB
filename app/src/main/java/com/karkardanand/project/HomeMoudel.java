package com.karkardanand.project;

public class HomeMoudel {

    private String caption;
    private String post_id;
    private int photo;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public HomeMoudel(String caption, String post_id, int photo) {
        this.caption = caption;
        this.post_id = post_id;
        this.photo = photo;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getcaption() {
        return caption;
    }

    public void setcaption(String caption) {
        this.caption = caption;
    }




    public HomeMoudel(String string, String money, String post_id, String caption, int photo) {
        this.post_id = post_id;

        this.caption = caption;

        this.photo = photo;
    }



    @Override
    public String toString() {
        return "{" +
                "post_id:'" + post_id + '\'' +
                ", caption:'" +  caption + '\'' +
                ", photo:'" +  photo + '\'' +
                '}';
    }
}


