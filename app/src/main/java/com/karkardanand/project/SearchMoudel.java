package com.karkardanand.project;

public class SearchMoudel {
    private String id;
    private String title;
    private String money;
    private String skills;
    private int photo;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }



    public SearchMoudel(String id, String money, String title, String skills, int photo) {
        this.id = id;
        this.money = money;
        this.title = title;
        this.skills = skills;
        this.photo = photo;
    }



    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", title:'" +  title + '\'' +
                ", money:'" +  money + '\'' +
                ", skills:'" +  skills + '\'' +
                ", photo:'" +  photo + '\'' +
                '}';
    }
}
