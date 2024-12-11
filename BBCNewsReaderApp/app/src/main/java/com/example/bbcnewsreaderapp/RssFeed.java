package com.example.bbcnewsreaderapp;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root(name = "rss", strict = false)
public class RssFeed {
    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Root(name = "channel", strict = false)
    public static class Channel {
        @ElementList(name = "item", inline = true)
        private List<NewsItem> items;

        public List<NewsItem> getItems() {
            return items;
        }
    }
}
