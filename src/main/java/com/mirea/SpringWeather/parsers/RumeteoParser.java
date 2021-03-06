package com.mirea.SpringWeather.parsers;

import com.mirea.SpringWeather.parsers.HtmlParser;
import model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RumeteoParser implements HtmlParser,Runnable {
    private Document doc;
    private String city;
    private Weather weather;

    @Override
    public void run() {
        getWeather(city);
    }

    @Override
    public float findWindSpeed() {
        String s = "0";
        Elements content = doc.getElementsByClass("more");
        for (Element i : content) {
            s = i.text();
            s = s.substring(s.indexOf("Ветер: ") + 7, s.indexOf("м/с") - 1);
            break;
        }
        s = s.replaceAll(",", ".");
        return Float.parseFloat(s);
    }

    @Override
    public int findHumidity() {
        String s = "0";
        Elements content = doc.getElementsByClass("more");

        for (Element i : content) {
            s = i.text();
            s = s.substring(s.indexOf("воздуха: ") + 9, s.indexOf("%"));
            break;
        }
        return Integer.parseInt(s);
    }

    @Override
    public int findTemperature() {
        String s = "0";
        int k = 0;
        Elements content = doc.getElementsByClass("temp");
        for (Element i : content) {
            s = i.text();
            s = s.substring(0, s.length() - 1);
            break;
        }
        if (s.contains("−")) {
            s = s.substring(1);
            return Math.round(Float.parseFloat(s)) * -1;
        } else {
            return Math.round(Float.parseFloat(s));
        }
    }

    @Override
    public Document parseDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (java.io.IOException IOException) {
            System.out.println("City not found");
        }
        return doc;
    }

    //@Override
    public void getWeather(String city) {
        doc = parseDoc("http://ru-meteo.ru/" + city);
        this.weather = new Weather("RuMeteo", findWindSpeed(), findTemperature(), findHumidity());
    }
    @Override
    public Weather getWeather() {
        return this.weather;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
