package com.karkardanand.project;

public class MyjobsMoudel {
    private int id;
    private String title;
    private String description;
    private String money;
    private String duration;
    private String skill;
    private Boolean isActive;

    public MyjobsMoudel(Long id, String money, String title, String skill, String isActive) {
    }


    public int getID() {
        return id;
    }

    public void setID(int value) {
        this.id = value;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String value) {
        this.money = value;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String value) {
        this.duration = value;
    }


    public String getSkill() {
        return skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setSkill(String value) {
        this.skill = value;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean value) {
        this.isActive = value;
    }


    @Override
    public String toString() {
        return "AllMyProject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", money='" + money + '\'' +
                ", duration='" + duration + '\'' +
                ", skill='" + skill + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}



