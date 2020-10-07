package com.yohanome.starwarsapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmResult {

    private String title;

    @SerializedName("episode_id")
    private int episodeId;

    @SerializedName("opening_crawl")
    private String openingCrawl;

    private String director;

    private String producer;

    @SerializedName("release_date")
    private String releaseDate;

    private List<String> characters;

    private String url;

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public String getUrl() {
        return url;
    }
}
