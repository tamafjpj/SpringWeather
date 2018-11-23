package com.mirea.SpringWeather;

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
    public ArrayList<Weather> getForecast(String city) {
        ArrayList<Weather> forecasts = new ArrayList<>();
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
        return forecasts;
    }

    public Weather resolveBest(ArrayList<Weather> forecasts) {
        return null;
    }

}
