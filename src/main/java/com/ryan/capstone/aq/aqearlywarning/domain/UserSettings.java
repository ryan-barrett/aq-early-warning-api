package com.ryan.capstone.aq.aqearlywarning.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class UserSettings {
    @Id
    private Integer id;
    private Integer userId;
    private Integer maxAqi;
    private Integer latitude;
    private Integer longitude;

    public UserSettings() {
    }

    UserSettings(Integer userId, Integer maxAqi, Integer longitude, Integer latitude) {
        this.userId = userId;
        this.maxAqi = maxAqi;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getMaxAqi() {
        return maxAqi;
    }

    public void setMaxAqi(Integer maxAqi) {
        this.maxAqi = maxAqi;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
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
