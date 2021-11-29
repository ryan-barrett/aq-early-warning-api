package com.ryan.capstone.aq.aqearlywarning.domain;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.time.LocalDateTime;

public class UserAccount implements UserDetails {
    @Id
    private int id;
    private String appleId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime lastChecked;
    private Boolean isSafe;

//    private String username;
//    private String password;
//    private Set<GrantedAuthority> roles = new HashSet<>();
//    private boolean active = true;

    UserAccount() {
    }

    public UserAccount(UserDTO user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.appleId = user.getAppleId();
        this.isSafe = user.getSafe();
    }

    public UserAccount(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.appleId = userAccount.getAppleId();
        this.email = userAccount.getEmail();
        this.firstName = userAccount.getFirstName();
        this.lastName = userAccount.getLastName();
        this.lastChecked = userAccount.getLastChecked();
        this.isSafe = userAccount.getSafe();
    }

    public UserAccount(String email, String firstName, String lastName, LocalDateTime lastChecked, Boolean isSafe) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastChecked = lastChecked;
        this.isSafe = isSafe;
    }

    public UserAccount(String appleId, String email, String firstName, String lastName, LocalDateTime lastChecked, Boolean isSafe) {
        this.appleId = appleId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastChecked = lastChecked;
        this.isSafe = isSafe;
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

    public Boolean getSafe() {
        return isSafe;
    }

    public void setSafe(Boolean safe) {
        isSafe = safe;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    @Override
    public String getPassword() {
        return this.appleId;
    }

    public void setPassword(String password) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
                ", isSafe=" + isSafe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id && Objects.equals(appleId, that.appleId) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(lastChecked, that.lastChecked) && Objects.equals(isSafe, that.isSafe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appleId, email, firstName, lastName, lastChecked, isSafe);
    }
}
