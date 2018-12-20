package model;

public class Weather {
    String source;
    int temp;
    int humidity;
    float windSpeed;

    public Weather(String source, float windSpeed, int temp, int humidity) {
        this.source = source;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.temp = temp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "source='" + source + '\'' +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
