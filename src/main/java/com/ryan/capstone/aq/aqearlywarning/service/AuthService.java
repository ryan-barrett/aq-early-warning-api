package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthPayload;
import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthResponse;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final String jwtIssuer = "https://appleid.apple.com";
    private final String jwtAudience = "t.aq-early-warning-ios";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public AuthService(@Autowired UserService userService) {
        this.userService = userService;
    }

    public IOSAuthResponse iosAuthenticate(IOSAuthPayload authPayload) {
        var isValidToken = validateIosToken(authPayload.getToken());

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

    private boolean validateIosToken(String token) {
        try {
            HttpsJwks httpsJkws = new HttpsJwks("https://appleid.apple.com/auth/keys");
            HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setVerificationKeyResolver(httpsJwksKeyResolver)
                    .setExpectedIssuer(jwtIssuer)
                    .setExpectedAudience(jwtAudience)
                    .build();

            var claim = jwtConsumer.processToClaims(token);
            logger.info("validated auth token for " + claim);
            return true;
        } catch (InvalidJwtException e) {
            logger.error(String.valueOf(e));
            return false;
        }
    }
}
