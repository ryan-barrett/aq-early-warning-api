package com.ryan.capstone.aq.aqearlywarning.domain;

import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionListItem;

public class PollutionStatusDTO {
    Float latitude;
    Float longitude;
    int aqi;
    PollutionListItem.Components aqiComponents;
    long date;

    public PollutionStatusDTO() {
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
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
}
