package com.mirea.SpringWeather;

import model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    public ArrayList<Weather> getForecast(@PathVariable String city, @RequestParam("trust") String trust) {
        return ws.getForecast(city, trust);
    }

}
