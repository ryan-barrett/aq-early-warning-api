package com.ryan.capstone.aq.aqearlywarning.domain.apple;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import reactor.core.Disposable;

public class IOSAuthResponse {
    Integer backendUserId;
    String email;
    String firstName;
    String lastName;
    Boolean success;

    public IOSAuthResponse() {
    }

    public IOSAuthResponse(Integer backendUserId, String email, String firstName, String lastName, Boolean success) {
        this.backendUserId = backendUserId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.success = success;
    }

    public IOSAuthResponse(UserAccount user) {
        this.backendUserId = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.success = true;
    }

    public IOSAuthResponse(Disposable user) {
        var castUser = (UserAccount) user;
        this.backendUserId = castUser.getId();
        this.email = castUser.getEmail();
        this.firstName = castUser.getFirstName();
        this.lastName = castUser.getLastName();
        this.success = true;
    }

    public Integer getBackendUserId() {
        return backendUserId;
    }

    public void setBackendUserId(Integer backendUserId) {
        this.backendUserId = backendUserId;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "IOSAuthResponse{" +
                "backendUserId=" + backendUserId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", success=" + success +
                '}';
    }
}
