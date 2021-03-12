package com.karkardanand.project;

public class SearchMoudel {
        private String id;
        private String title;
        private String description;
        private String money;
        private String duration;
        private String photo;

    public SearchMoudel(String id, String money, String title, String photo) {

    }


    public String getID() { return id; }

        public void setID(String value) { this.id = value; }


        public String getTitle() { return title; }

        public void setTitle(String value) { this.title = value; }


        public String getDescription() { return description; }

        public void setDescription(String value) { this.description = value; }


        public String getMoney() { return money; }

        public void setMoney(String value) { this.money = value; }


        public String getDuration() { return duration; }

        public void setDuration(String value) { this.duration = value; }


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
        return "projectList{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", money='" + money + '\'' +
                ", duration='" + duration + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}

