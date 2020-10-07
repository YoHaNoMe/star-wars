package com.yohanome.starwarsapplication.models;

import java.util.List;

public class FilmModel {
    private int count;

    private String next;

    private String previous;

    private List<FilmResult> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<FilmResult> getResults() {
        return results;
    }
}
