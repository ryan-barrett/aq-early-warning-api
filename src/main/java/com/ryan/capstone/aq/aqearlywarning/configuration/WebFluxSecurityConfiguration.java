package com.ryan.capstone.aq.aqearlywarning.configuration;

import com.ryan.capstone.aq.aqearlywarning.AuthenticationManager;
import com.ryan.capstone.aq.aqearlywarning.SecurityContextRepository;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfiguration {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    WebFluxSecurityConfiguration(@Autowired UserService userService,
                                 @Autowired AuthenticationManager authenticationManager,
                                 @Autowired SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return userService::getUserDetailsByEmail;
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers("/authenticate", "/authenticate/apple").permitAll()
                                .anyExchange().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint((response, error) -> Mono.fromRunnable(() -> {
                    response.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((response, error) -> Mono.fromRunnable(() -> {
                    response.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                }))
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .requestCache().requestCache(NoOpServerRequestCache.getInstance())
                .and()
                .build();
    }
}
