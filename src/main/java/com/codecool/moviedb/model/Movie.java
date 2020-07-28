package com.codecool.moviedb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {

    private double popularity;
    private String title;
    private String language;
    private String posterString;
    private int id;
    private Date releaseDate;
    private String overview;
    private List<String> genre = new ArrayList<>();

    public Movie(double popularity, String title, String language, String posterString, int id, Date releaseDate, String overview, List<String> genre) {
        this.popularity = popularity;
        this.title = title;
        this.language = language;
        this.posterString = posterString;
        this.id = id;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.genre = genre;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getPosterString() {
        return posterString;
    }

    public int getId() {
        return id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getGenre() {
        return genre;
    }
}
