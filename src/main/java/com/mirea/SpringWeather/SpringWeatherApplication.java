package com.mirea.SpringWeather;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWeatherApplication {

    public static void main(String[] args) {

        //SpringApplication.run(SpringWeatherApplication.class, args);
        AccuParser yp = new AccuParser();
        Weather w = yp.getWeather("saint-petersburg");
        System.out.println(w.source);
        System.out.println(w.temp);
        System.out.println(w.humidity);
        System.out.println(w.windSpeed);
    }
}
