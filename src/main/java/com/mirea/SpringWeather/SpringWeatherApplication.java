package com.mirea.SpringWeather;

import model.Weather;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWeatherApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringWeatherApplication.class, args);
        /*AccuParser yp = new AccuParser();
        Weather w = yp.getWeather("sochi");
        System.out.println(w.getSource());
        System.out.println(w.getTemp());
        System.out.println(w.getHumidity());
        System.out.println(w.getWindSpeed());*/
    }
}
