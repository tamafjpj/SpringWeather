package com.mirea.SpringWeather;

public class Weather {
    int temp;
    int humidity;
    float windSpeed;

    Weather(float windSpeed, int temp, int humidity) {
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.temp = temp;
    }
}
