package com.example.ordersystem;

public class Movie {
    public int img;
    public String movie_name;
    public String date;
    public String info;

    public Movie(int img, String movie_name, String date, String info) {
        this.img = img;
        this.movie_name = movie_name;
        this.date = date;
        this.info = info;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
