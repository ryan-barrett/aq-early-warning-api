package com.ryan.capstone.aq.aqearlywarning.domain;

import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthResponse;

public class AuthenticationResponse {
    private final String jwt;
    private final IOSAuthResponse iosResponse;

    public AuthenticationResponse(String jwt, IOSAuthResponse iosResponse) {
        this.jwt = jwt;
        this.iosResponse = iosResponse;
    }

    public String getJwt() {
        return jwt;
    }

    public IOSAuthResponse getIosResponse() {
        return iosResponse;
    }
}
