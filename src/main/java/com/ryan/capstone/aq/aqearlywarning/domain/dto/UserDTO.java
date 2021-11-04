package com.ryan.capstone.aq.aqearlywarning.domain.dto;

import java.util.Objects;

public class UserDTO {
    Integer id;
    Integer maxAqi;
    Double longitude;
    Double latitude;
    String email;
    String firstName;
    String lastName;
    Integer currentAqi;

    public UserDTO() {
    }

    public UserDTO(UserDTO user) {
        this.id = user.getId();
        this.maxAqi = user.getMaxAqi();
        this.longitude = user.getLongitude();
        this.latitude = user.getLatitude();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.currentAqi = user.getCurrentAqi();
    }

    public UserDTO(Integer id, Integer maxAqi, Double longitude, Double latitude, String email, String firstName, String lastName) {
        this.id = id;
        this.maxAqi = maxAqi;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCurrentAqi() {
        return currentAqi;
    }

    public void setCurrentAqi(Integer currentAqi) {
        this.currentAqi = currentAqi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(maxAqi, userDTO.maxAqi) && Objects.equals(longitude, userDTO.longitude) && Objects.equals(latitude, userDTO.latitude) && Objects.equals(email, userDTO.email) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxAqi, longitude, latitude, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", maxAqi=" + maxAqi +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currentAqi=" + currentAqi +
                '}';
    }
}
