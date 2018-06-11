package com.example.android.popularmusic.data;

public class Movies {
    private String Title;
    private String PosterPath;
    private String PublishedDate;
    private double Average;
    private String Synopsis;


    public Movies(String title, String posterPath, String publishedDate, double average, String synopsis) {
        Title = title;
        PosterPath = posterPath;
        PublishedDate = publishedDate;
        Average = average;
        Synopsis = synopsis;
    }


    public String getTitle() {
        return Title;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public String getPublishedDate() {
        return PublishedDate;
    }

    public Double getAverage() {
        return Average;
    }

    public String getSynopsis() {
        return Synopsis;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "Titile='" + Title + '\'' +
                ", PosterPath='" + PosterPath + '\'' +
                ", PublishedDate='" + PublishedDate + '\'' +
                ", Average='" + Average + '\'' +
                ", Synopsis='" + Synopsis +
                '}';
    }

}
