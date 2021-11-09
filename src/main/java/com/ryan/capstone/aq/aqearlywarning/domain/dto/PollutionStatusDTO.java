package com.ryan.capstone.aq.aqearlywarning.domain.dto;

import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionListItem;

import java.util.Objects;

public class PollutionStatusDTO {
    double latitude;
    double longitude;
    int aqi;
    PollutionListItem.Components aqiComponents;
    long date;

    public PollutionStatusDTO() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public PollutionListItem.Components getAqiComponents() {
        return aqiComponents;
    }

    public void setAqiComponents(PollutionListItem.Components aqiComponents) {
        this.aqiComponents = aqiComponents;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollutionStatusDTO that = (PollutionStatusDTO) o;
        return aqi == that.aqi && date == that.date && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(aqiComponents, that.aqiComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, aqi, aqiComponents, date);
    }

    @Override
    public String toString() {
        return "PollutionStatusDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", aqi=" + aqi +
                ", aqiComponents=" + aqiComponents +
                ", date=" + date +
                '}';
    }
}
