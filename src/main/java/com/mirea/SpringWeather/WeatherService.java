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
        ArrayList<Weather> foreacsts = new ArrayList<>();
        foreacsts.add(yp.getWeather(city));
        foreacsts.add(ap.getWeather(city));
        foreacsts.add(rp.getWeather(city));
        foreacsts.add(mp.getWeather(city));
        return foreacsts;
    }

    public Weather resolveBest(ArrayList<Weather> forecasts) {
        return null;
    }

}
