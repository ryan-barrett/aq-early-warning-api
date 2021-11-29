package com.ryan.capstone.aq.aqearlywarning.domain.dto;

import java.util.Objects;

public class UserDTO {
    int id;
    String appleId;
    Integer maxAqi;
    Double longitude;
    Double latitude;
    String email;
    String firstName;
    String lastName;
    Boolean isSafe;
    int currentAqi;

    public UserDTO() {
    }

    public UserDTO(UserDTO user) {
        this.id = user.getId();
        this.appleId = user.getAppleId();
        this.maxAqi = user.getMaxAqi();
        this.longitude = user.getLongitude();
        this.latitude = user.getLatitude();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.currentAqi = user.getCurrentAqi();
        this.isSafe = user.getSafe();
    }

    public UserDTO(int id, int maxAqi, double longitude, double latitude, String email, String firstName, String lastName, Boolean isSafe) {
        this.id = id;
        this.maxAqi = maxAqi;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSafe = isSafe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public Integer getMaxAqi() {
        return maxAqi;
    }

    public void setMaxAqi(Integer maxAqi) {
        this.maxAqi = maxAqi;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCurrentAqi() {
        return currentAqi;
    }

    public void setCurrentAqi(int currentAqi) {
        this.currentAqi = currentAqi;
    }

    public Boolean getSafe() {
        return isSafe;
    }

    public void setSafe(Boolean safe) {
        isSafe = safe;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", appleId='" + appleId + '\'' +
                ", maxAqi=" + maxAqi +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isSafe=" + isSafe +
                ", currentAqi=" + currentAqi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && currentAqi == userDTO.currentAqi && Objects.equals(appleId, userDTO.appleId) && Objects.equals(maxAqi, userDTO.maxAqi) && Objects.equals(longitude, userDTO.longitude) && Objects.equals(latitude, userDTO.latitude) && Objects.equals(email, userDTO.email) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(isSafe, userDTO.isSafe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appleId, maxAqi, longitude, latitude, email, firstName, lastName, isSafe, currentAqi);
    }
}
