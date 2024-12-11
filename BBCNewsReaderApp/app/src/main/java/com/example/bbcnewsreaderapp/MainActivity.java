package com.example.bbcnewsreaderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import com.example.bbcnewsreaderapp.NewsAdapter;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private final List<NewsItem> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter newsAdapter = new NewsAdapter(newsList, this::onNewsItemClicked);
        recyclerView.setAdapter(newsAdapter);

        fetchNews(newsAdapter);
    }

    private void fetchNews(NewsAdapter newsAdapter) {
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://feeds.bbci.co.uk/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        BBCNewsService service = retrofit.create(BBCNewsService.class);
        service.getNews().enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(@NonNull Call<RssFeed> call, @NonNull Response<RssFeed> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    int previousSize = newsList.size();
                    newsList.addAll(response.body().getChannel().getItems());
                    newsAdapter.notifyItemRangeInserted(previousSize, newsList.size() - previousSize);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeed> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onNewsItemClicked(NewsItem item) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("link", item.getLink());
        startActivity(intent);
    }
}
