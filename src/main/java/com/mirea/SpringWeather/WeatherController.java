package com.mirea.SpringWeather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {
    WeatherService weatherService;

    @Autowired
    WeatherController() {
        this.weatherService = new WeatherService();
    }

   /* @RequestMapping(value = "/weather/{city}", method = RequestMethod.GET)
    @ResponseBody();*/
}
