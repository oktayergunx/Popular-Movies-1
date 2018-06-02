package com.example.oktay.popularmovies1.utilities;

public class Movie {

    private String title;

    //constructor
    public Movie(){
    }

    public Movie(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
