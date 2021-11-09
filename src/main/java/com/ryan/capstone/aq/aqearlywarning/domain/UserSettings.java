package com.ryan.capstone.aq.aqearlywarning.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class UserSettings {
    @Id
    private int id;
    private int userId;
    private Integer maxAqi;
    private Double latitude;
    private Double longitude;

    public UserSettings() {
    }

    public UserSettings(Integer userId, Integer maxAqi, Double longitude, Double latitude) {
        this.userId = userId;
        this.maxAqi = maxAqi;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public UserSettings(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public Integer getMaxAqi() {
        return maxAqi;
    }

    public void setMaxAqi(Integer maxAqi) {
        this.maxAqi = maxAqi;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "id=" + id +
                ", user_id=" + userId +
                ", maxAqi=" + maxAqi +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettings that = (UserSettings) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(maxAqi, that.maxAqi) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, maxAqi, latitude, longitude);
    }
}
