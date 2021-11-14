package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthPayload;
import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthResponse;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {
    private final String jwtIssuer = "https://appleid.apple.com";
    private final String jwtAudience = "t.aq-early-warning-ios";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    private final HttpsJwks httpsJkws = new HttpsJwks("https://appleid.apple.com/auth/keys");
    private final HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);

    private final JwtConsumer jwtConsumer = new JwtConsumerBuilder()
            .setVerificationKeyResolver(httpsJwksKeyResolver)
            .setExpectedIssuer(jwtIssuer)
            .setExpectedAudience(jwtAudience)
            .build();

    public AuthService(@Autowired UserService userService) {
        this.userService = userService;
    }

    public IOSAuthResponse iosLogin(String token, IOSAuthPayload authPayload) {
        var isValidToken = validateIosToken(token) != null;

        if (!isValidToken) {
            throw new Error("invalid jwt token");
        }
        var existingUser = userService.getUserAccountByAppleId(authPayload.getUserId()).toProcessor().block();

        if (existingUser != null) {
            return new IOSAuthResponse(existingUser);
        }

        var user = userService.createUserAccount(authPayload.getUserId(), authPayload.getEmail(),
                authPayload.getFirstName(), authPayload.getLastName()).toProcessor().block();

        assert user != null;
        return new IOSAuthResponse(user);
    }

    public boolean iosAuth(String token) throws AuthenticationException {
        JwtClaims claim = validateIosToken(token);

        if (claim == null) {
            throw new AuthenticationException("invalid jwt");
        }

        String userEmail = (String) claim.getClaimsMap().get("email");
        String aud = (String) claim.getClaimsMap().get("aud");

        if (!aud.equals(jwtAudience)) {
            throw new AuthenticationException("invalid jwt");
        }
        var user = userService.getUserAccountByEmail(userEmail).toProcessor().block();

        if (user == null) {
            throw new AuthenticationException("invalid jwt");
        }

        if (user.getAppleId() == null) {
            throw new AuthenticationException("invalid jwt");
        }
        return true;
    }

    private JwtClaims validateIosToken(String tokenHeader) {
        try {
            var token = tokenHeader.replace("bearer", "").replace("Bearer", "").trim();

            return jwtConsumer.processToClaims(token);
        } catch (InvalidJwtException e) {
            logger.error(String.valueOf(e));
            return null;
        }
    }
}
