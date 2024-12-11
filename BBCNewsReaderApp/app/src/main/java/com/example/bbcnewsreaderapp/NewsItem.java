package com.example.bbcnewsreaderapp;


public class NewsItem {
    private String title;
    private String link;
    private String description;

    public NewsItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
