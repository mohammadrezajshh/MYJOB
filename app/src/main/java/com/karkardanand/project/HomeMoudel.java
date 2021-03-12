package com.karkardanand.project;

public class HomeMoudel {

    private String caption;
    private String id;
    private String photo;
    private String profilePic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public HomeMoudel(String caption, String id, String profilePic, String photo) {
        this.caption = caption;
        this.id = id;
        this.photo = photo;
        this.profilePic = profilePic;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getcaption() {
        return caption;
    }

    public void setcaption(String caption) {
        this.caption = caption;
    }




    public HomeMoudel(String string, String profilePic, String id, String caption, String photo) {
        this.id = id;

        this.caption = caption;

        this.photo = photo;
        this.profilePic = profilePic;
    }


    @Override
    public String toString() {
        return "postsList{" +
                "caption='" + caption + '\'' +
                ", id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}


