package com.mirea.SpringWeather;

import model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class WeatherController {
    private WeatherService ws;
    @Autowired
    WeatherController(WeatherService ws) {
        this.ws = ws;
    }

    @RequestMapping(value = "/weather/{city}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Weather> getForecast(@PathVariable String city) {
        return ws.getForecast(city);
    }

}
