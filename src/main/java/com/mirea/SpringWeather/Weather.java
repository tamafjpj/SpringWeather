package com.mirea.SpringWeather;

public class Weather {
    String source;
    int temp;
    int humidity;
    float windSpeed;

    Weather(String source, float windSpeed, int temp, int humidity) {
        this.source = source;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.temp = temp;
    }
}
