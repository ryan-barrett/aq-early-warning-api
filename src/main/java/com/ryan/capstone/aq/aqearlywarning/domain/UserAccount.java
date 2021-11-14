package com.ryan.capstone.aq.aqearlywarning.domain;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.time.LocalDateTime;

public class UserAccount {
    @Id
    private int id;
    private String appleId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime lastChecked;

    UserAccount() {
    }

    public UserAccount(UserDTO user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.appleId = user.getAppleId();
    }

    public UserAccount(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.appleId = userAccount.getAppleId();
        this.email = userAccount.getEmail();
        this.firstName = userAccount.getFirstName();
        this.lastName = userAccount.getLastName();
        this.lastChecked = userAccount.getLastChecked();
    }

    public UserAccount(String email, String firstName, String lastName, LocalDateTime lastChecked) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastChecked = lastChecked;
    }

    public UserAccount(String appleId, String email, String firstName, String lastName, LocalDateTime lastChecked) {
        this.appleId = appleId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastChecked = lastChecked;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalDateTime lastChecked) {
        this.lastChecked = lastChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id && Objects.equals(appleId, that.appleId) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(lastChecked, that.lastChecked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appleId, email, firstName, lastName, lastChecked);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", appleId='" + appleId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastChecked=" + lastChecked +
                '}';
    }
}
