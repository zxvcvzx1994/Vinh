package com.example.kh.vinh.Module;

import java.util.ArrayList;

/**
 * Created by kh on 4/20/2017.
 */

public class Movies {


    public Movies(){


    }
    private String movie;
    private int year;
    private double rating;
    private String duration;
    private String tagline;
    private String director;
    private String image;
    private String story;
    private ArrayList<cast> cast;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public ArrayList<Movies.cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Movies.cast> cast) {
        this.cast = cast;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static class cast{
        public cast(){

        }
        private String name;
    public cast(String name){

        this.name = name;
    }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

