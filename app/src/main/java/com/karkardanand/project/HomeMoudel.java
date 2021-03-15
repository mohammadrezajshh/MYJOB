package com.karkardanand.project;

public class HomeMoudel {
    private String profilePic;
    private int id;
    private String caption;
    private String photo;

    public HomeMoudel(String profilePic, int id, String caption, String photo) {
        this.profilePic = profilePic;
        this.id = id;
        this.caption = caption;
        this.photo = photo;
    }

    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String value) { this.profilePic = value; }

    public int getID() { return id; }
    public void setID(int value) { this.id = value; }

    public String getCaption() { return caption; }
    public void setCaption(String value) { this.caption = value; }

    public String getPhoto() { return photo; }
    public void setPhoto(String value) { this.photo = value; }

    @Override
    public String toString() {
        return "postsList{" +
                "profilePic='" + profilePic + '\'' +
                ", id=" + id +
                ", caption='" + caption + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}