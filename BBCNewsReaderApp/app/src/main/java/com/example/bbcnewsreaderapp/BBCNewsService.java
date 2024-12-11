package com.example.bbcnewsreaderapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BBCNewsService {
    @GET("news/rss.xml")
    Call<RssFeed> getNews();
}