package com.mirea.SpringWeather.parsers;

import com.mirea.SpringWeather.parsers.HtmlParser;
import model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class MsnParser implements HtmlParser,Runnable {
    private Document doc;
    private String city;
    private Weather weather;

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

    @Override
    public int findTemperature() {
        String s = "0";
        Elements content = doc.getElementsByClass("current-info");
        for (Element i : content) {
            s = i.text();
            break;
        }
        if (s.contains("−")) {
            s = s.substring(1, s.length()-6);
            return Integer.parseInt(s) * -1;
        } else {
            s = s.substring(0, s.length() - 6);
            return Integer.parseInt(s);
        }
    }

    @Override
    public int findHumidity() {
        String s = "0";
        int k = 1;
        Elements content = doc.getElementsByClass("weather-info");
        for (Element i : content) {
            k++;
            if (k == 2) {
                s = i.text();
                s = s.substring(s.indexOf("Влажность") +10, s.indexOf("%"));
            }
        }
        return Integer.parseInt(s);
    }

    @Override
    public float findWindSpeed() {
        String s = "";
        int k = 1;
        Elements content = doc.getElementsByClass("weather-info");

        for (Element i : content) {
            k++;
            if (k == 2) {
                s = i.text();
                s = s.substring(s.indexOf("Ветер"), s.indexOf("/")-2);
                s = s.substring(s.indexOf(" ") + 1);
                s = s.substring(s.indexOf(" ") + 1);
            }
        }
        s = s.replaceAll(",", ".");
        return Float.parseFloat(s);
    }

   // @Override
    public void getWeather(String city) {
        city = city.toLowerCase();
        String url;
        switch (city) {
            case "moscow":
                url = "https://www.msn.com/ru-ru/weather/today/%D0%BC%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%D1%86%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D1%84%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%BE%D0%BA%D1%80%D1%83%D0%B3%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?iso=RU&el=bE2etLln6VwmkgIjk3OYug%3D%3D&lat=55.980&long=37.180";
                break;
            case "vladivostok":
                url = "https://www.msn.com/ru-ru/weather/today/%D0%B2%D0%BB%D0%B0%D0%B4%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D0%BE%D0%BA%D0%BF%D1%80%D0%B8%D0%BC%D0%BE%D1%80%D1%81%D0%BA%D0%B8%D0%B9-%D0%BA%D1%80%D0%B0%D0%B9%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?q=%D0%92%D0%BB%D0%B0%D0%B4%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D0%BE%D0%BA-%D0%9F%D1%80%D0%B8%D0%BC%D0%BE%D1%80%D1%81%D0%BA%D0%B8%D0%B9-%D0%BA%D1%80%D0%B0%D0%B9&form=PRWLAS&iso=RU&el=bmJF1h8TGxmkrcqM7MSAMA%3d%3d";
                break;
            case "saint-petersburg":
                url = "https://www.msn.com/ru-ru/weather/today/%D1%81%D0%B0%D0%BD%D0%BA%D1%82-%D0%BF%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%D1%81%D0%B0%D0%BD%D0%BA%D1%82-%D0%BF%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?q=%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3&form=PRWLAS&iso=RU&el=NFNU2bI%2b4%2fnH%2fziMr%2fsY3g%3d%3d";
                break;
            case "kazan":
                url = "https://www.msn.com/ru-ru/weather/today/%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C%D1%80%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0-%D1%82%D0%B0%D1%82%D0%B0%D1%80%D1%81%D1%82%D0%B0%D0%BD%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?q=%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C-%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0-%D0%A2%D0%B0%D1%82%D0%B0%D1%80%D1%81%D1%82%D0%B0%D0%BD&form=PRWLAS&iso=RU&el=xJXlqI69unpxA1aHpkZTnQ%3d%3d";
                break;
            case "sochi":
                url = "https://www.msn.com/ru-ru/weather/today/%D1%81%D0%BE%D1%87%D0%B8%D0%BA%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D0%B4%D0%B0%D1%80%D1%81%D0%BA%D0%B8%D0%B9-%D0%BA%D1%80%D0%B0%D0%B9%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?q=%D0%A1%D0%BE%D1%87%D0%B8-%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D0%B4%D0%B0%D1%80%D1%81%D0%BA%D0%B8%D0%B9-%D0%BA%D1%80%D0%B0%D0%B9&form=PRWLAS&iso=RU&el=MeCZFNDgRipZAmp5ULI7mA%3d%3d";
                break;
            default:
                url = "https://www.msn.com/ru-ru/weather/today/%D0%BC%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%D1%86%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D1%84%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%BE%D0%BA%D1%80%D1%83%D0%B3%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F/we-city?iso=RU&el=bE2etLln6VwmkgIjk3OYug%3D%3D&lat=55.980&long=37.180";

        }
        doc = parseDoc(url);
        this.weather=new Weather("MsnWeather", findWindSpeed(), findTemperature(), findHumidity());
    }

    @Override
    public Weather getWeather() {
        return this.weather;
    }

    public void setCity(String city) {
        this.city = city;
    }
}