package com.mirea.SpringWeather;

import org.jsoup.nodes.Document;

public interface HtmlParser {
    Document parseDoc(String url);

    int findTemperature();

    float findWindSpeed();

    int findHumidity();
}
