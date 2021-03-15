package com.karkardanand.project;

public class AllRequest {


        private long id;
        private String description;
        private String money;
        private String duration;

        private long idProject;
        private String token;
        public AllRequest(long id, String description, Long idProject, String token, String money, String duration) {
            this.id= id;
            this.description= description;
            this.idProject= idProject;
            this.token= token;
            this.money= money;
            this.duration= duration;
        }


        @Override
        public String toString() {
            return "AllRequest{" +
                    "id=" + id +
                    ", description='" + description + '\'' +
                    ", money='" + money + '\'' +
                    ", duration='" + duration + '\'' +
                    ", idProject=" + idProject +
                    ", token='" + token + '\'' +
                    '}';
        }

        public AllRequest(long id, String description, String money, String duration, long idProject, String token) {
            this.id = id;
            this.description = description;
            this.money = money;
            this.duration = duration;
            this.idProject = idProject;
            this.token = token;
        }

        public long getID() { return id; }

        public void setID(long value) { this.id = value; }


        public String getDescription() { return description; }

        public void setDescription(String value) { this.description = value; }


        public String getMoney() { return money; }

        public void setMoney(String value) { this.money = value; }


        public String getDuration() { return duration; }

        public void setDuration(String value) { this.duration = value; }




        public long getIDProject() { return idProject; }

        public void setIDProject(long value) { this.idProject = value; }


        public String getToken() { return token; }

        public void setToken(String value) { this.token = value; }
    }


