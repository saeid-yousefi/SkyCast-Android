package com.sy.common_domain.model.weather

enum class WeatherType {
    SUNNY,
    CLOUDY,
    RAINY,
    SNOWY,
    WINDY,
    STORMY,
    FOGGY,
    UNKNOWN // For unmapped or unexpected API responses
}