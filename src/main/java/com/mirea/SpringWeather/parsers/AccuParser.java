package com.mirea.SpringWeather.parsers;

import model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AccuParser implements HtmlParser,Runnable {
    private Document doc;
    private Weather weather;
    private String city;

    @Override
    public void run() {
        getWeather(city);
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

        @Override
        public int findTemperature () {
            String s = "0";
            Elements content = doc.getElementsByClass("large-temp");

            for (Element i : content) {
                s = i.text();
                break;
            }
            if (s.contains("−")) {
                s = s.substring(1, s.length() - 1);
                return Integer.parseInt(s) * -1;
            } else {
                s = s.substring(0, s.length() - 1);
                return Integer.parseInt(s);
            }
        }

        @Override
        public int findHumidity () {
            String s = "0";
            int k = 1;
            Elements content = doc.getElementsByClass("stats");

            for (Element i : content) {
                k++;
                if (k == 2) {
                    s = i.text();
                    s = s.substring(s.indexOf("Влажность:") + 11, s.indexOf("%"));
                }
            }
            return Integer.parseInt(s);
        }

        @Override
        public float findWindSpeed () {
            String s = "0";
            int k = 1;
            Elements content = doc.getElementsByClass("stats");

            for (Element i : content) {
                k++;
                if (k == 2) {
                    s = i.text();
                    s = s.substring(s.indexOf("км/ч") - 3, s.indexOf("км/ч") - 1);
                }
            }
            s = s.replaceAll(",", ".");
            return (float) (Math.round(Float.parseFloat(s) / (float) 3.6));
        }

        public void getWeather (String city){
            city = city.toLowerCase();
            String url;
            switch (city) {
                case "moscow":
                    url = "https://www.accuweather.com/ru/ru/moscow/294021/current-weather/294021";
                    break;
                case "vladivostok":
                    url = "https://www.accuweather.com/ru/ru/vladivostok/294927/current-weather/294927";
                    break;
                case "saint-petersburg":
                    url = "https://www.accuweather.com/ru/ru/saint-petersburg/295212/current-weather/295212";
                    break;
                case "kazan":
                    url = "https://www.accuweather.com/ru/ru/kazan/295954/current-weather/295954";
                    break;
                case "sochi":
                    url = "https://www.accuweather.com/ru/ru/sochi/293687/current-weather/293687";
                    break;
                default:
                    url = "https://www.accuweather.com/ru/ru/moscow/294021/current-weather/294021";

            }
            doc = parseDoc(url);
            this.weather = new Weather("AccuWeather", findWindSpeed(), findTemperature(), findHumidity());
        }

        public Weather getWeather () {
            return weather;
        }

        public void setCity (String city){
            this.city = city;
        }
    }
