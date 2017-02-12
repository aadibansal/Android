package com.example.aditya.weather_webservice;

/**
 * Created by Aditya on 2/8/2017.
 */

public class WeatherInfo {

    public final Coord coord;
    public final Sys sys;
    public final Weather[] weather;
    public final Main main;
    public final Wind wind;
    public final String name;
    public final String dt;

    public WeatherInfo(Coord coord, Sys sys, Weather[] weather, Main main, Wind wind, String name, String dt) {
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.name = name;
        this.dt = dt;
    }

    class Coord {
        public final double lon;
        public final double lat;

        Coord(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
        }
    }

    class Sys {
        public final String country;

        Sys(String country) {
            this.country = country;
        }
    }

    class Weather {
        public final String description;

        Weather(String description) {
            this.description = description;
        }
    }

    class Main {
        public final double temp;
        public final double humidity;
        public final double temp_min;
        public final double temp_max;

        Main(double temp, double humidity, double temp_min, double temp_max) {
            this.temp = temp;
            this.humidity = humidity;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }
    }

    class Wind {
        public final double speed;
        public final double deg;

        Wind(double speed, double deg) {
            this.speed = speed;
            this.deg = deg;
        }
    }
}
