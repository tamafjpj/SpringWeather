package com.mirea.SpringWeather.parsers;

import com.mirea.SpringWeather.parsers.HtmlParser;
import model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class YandexParser implements HtmlParser,Runnable {
    private Document doc;
    private String city;
    private  Weather weather;

    @Override
    public void run() {
        getWeather(city);
    }

    public Document parseDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (java.io.IOException IOException) {
            System.out.println("City not found");
        }
        return doc;
    }

    public int findTemperature() {
        String s = "0";
        Elements content = doc.getElementsByClass("temp__value");
        for (Element i : content) {
            s = i.text();
            break;
        }
        if (s.contains("−")) {
            s = s.substring(1, s.length());
            return Integer.parseInt(s) * -1;
        } else
            return Integer.parseInt(s);
    }

    public float findWindSpeed() {
        String s = "0";
        Elements content = doc.getElementsByClass("wind-speed");
        for (Element i : content) {
            s = i.text();
        }
        s = s.replaceAll(",", ".");
        return Float.parseFloat(s);
    }

    public int findHumidity() {
        int k = 0;
        String s = "00";
        Elements content = doc.getElementsByClass("term__value");
        for (Element i : content) {
            k++;
            if (k == 5) {
                s = i.text();
                break;
            }
        }
        s = s.substring(0, 2);
        return Integer.parseInt(s);
    }

    public void getWeather(String city) {
        this.doc = parseDoc("https://yandex.ru/pogoda/" + city);
        this.weather = new Weather("YandexWeather", findWindSpeed(), findTemperature(), findHumidity());
    }

    @Override
    public Weather getWeather() {
        return this.weather;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
