package com.mirea.SpringWeather;

import com.mirea.SpringWeather.parsers.AccuParser;
import com.mirea.SpringWeather.parsers.MsnParser;
import com.mirea.SpringWeather.parsers.RumeteoParser;
import com.mirea.SpringWeather.parsers.YandexParser;
import model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class WeatherService {
    private YandexParser yp;
    private AccuParser ap;
    private RumeteoParser rp;
    private MsnParser mp;

    @Autowired
    public WeatherService(YandexParser yp, AccuParser ap, RumeteoParser rp,MsnParser mp) {
        this.ap = ap;
        this.rp = rp;
        this.yp = yp;
        this.mp = mp;

    }

    public ArrayList<Weather> getForecast(String city, String trust) {
        ArrayList<Weather> forecasts = new ArrayList<>();
        Weather trustW = null;
        ap.setCity(city);
        yp.setCity(city);
        rp.setCity(city);
        mp.setCity(city);
        Thread myTread1 = new Thread(ap);
        Thread myTread2 = new Thread(yp);
        Thread myTread3 = new Thread(mp);
        Thread myTread4 = new Thread(rp);
        try {
            myTread1.start();
            myTread2.start();
            myTread3.start();
            myTread4.start();
            myTread1.join();
            myTread2.join();
            myTread3.join();
            myTread4.join();
        }catch (InterruptedException e){e.printStackTrace();}
        forecasts.add(yp.getWeather());
        forecasts.add(ap.getWeather());
        forecasts.add(rp.getWeather());
        forecasts.add(mp.getWeather());
        for (Weather i : forecasts) {
            if (i.getSource().toLowerCase().equals(trust.toLowerCase()))
                trustW = i;
        }
        forecasts.add(resolveBest(forecasts, trustW));
        return forecasts;
    }

    public Weather resolveBest(ArrayList<Weather> forecasts, Weather trust) {
        ArrayList<Weather> res = new ArrayList<>();
        ArrayList<Weather> res1 = new ArrayList<>();
        ArrayList<Weather> res2 = new ArrayList<>();
        int k = 0;
        int maxc = 1;
        for (Weather i : forecasts) {
            for (Weather j : forecasts) {
                if (j.getTemp() == i.getTemp()) k++;
            }
            res.add(i);
            if (k > maxc) maxc = k;
            k = 0;
        }
        k = 0;
        if (res.size() > 1) {
            for (Weather i : res) {
                for (Weather j : res) {
                    if (j.getTemp() == i.getTemp()) k++;
                }
                if (k == maxc) res1.add(i);
                k = 0;
            }
        } else return res.get(0);
        k = 0;
        maxc = 1;
        for (Weather i : res1) {
            for (Weather j : res1) {
                if (j.getHumidity() == i.getHumidity()) k++;
            }
            if (k > maxc) maxc = k;
            k = 0;
        }
        for (Weather i : res1) {
            for (Weather j : res1) {
                if (j.getHumidity() == i.getHumidity()) k++;
            }
            if (k == maxc) res2.add(i);
            k = 0;
        }
        k = 0;
        maxc = 1;
        for (Weather i : res2) {
            for (Weather j : res2) {
                if (j.getWindSpeed() == i.getWindSpeed()) k++;
            }
            if (k > maxc) maxc = k;
            k = 0;
        }
        res.clear();
        for (Weather i : res2) {
            for (Weather j : res2) {
                if (j.getWindSpeed() == i.getWindSpeed()) k++;
            }
            if (k == maxc) res.add(i);
            k = 0;
        }
        if (res.size() > 1) {
            for (Weather i : res) {
                //System.out.println(i.toString());
                if (i.getSource().equalsIgnoreCase(trust.getSource())) return i;
            }
            return res.get(0);
        }
        return res.get(0);
    }
}
