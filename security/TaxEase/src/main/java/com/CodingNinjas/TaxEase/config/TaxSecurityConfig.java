package com.codingNinjas.taxEase.config;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

import com.codingNinjas.taxEase.repository.UserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TaxSecurityConfig {

    private final UserRepository userRepository;
    public TaxSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/user/signup")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )

                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/all", true)
                        .userInfoEndpoint(userInfo ->
                                userInfo.userAuthoritiesMapper(userAuthoritiesMapper())
                        )
                )

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Collection<GrantedAuthority> authorities =
                    new ArrayList<>();

            Map<String, Object> realmAccess =
                    jwt.getClaim("realm_access");

            if (realmAccess != null) {

                List<String> roles =
                        (List<String>) realmAccess.get("roles");

                if (roles != null) {
                    roles.forEach(role ->
                            authorities.add(
                                    new SimpleGrantedAuthority(
                                            "ROLE_" + role.toUpperCase()
                                    )
                            )
                    );
                }
            }

            return authorities;
        });

        return converter;
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {

        return authorities -> {

            Set<GrantedAuthority> mappedAuthorities =
                    new HashSet<>();

            authorities.forEach(authority -> {

                if (authority instanceof OidcUserAuthority oidcUserAuthority) {

                    Map<String, Object> userAttributes =
                            oidcUserAuthority.getUserInfo().getClaims();

                    Map<String, Object> realmAccess =
                            (Map<String, Object>) userAttributes.get("realm_access");

                    if (realmAccess != null) {

                        List<String> roles =
                                (List<String>) realmAccess.get("roles");

                        if (roles != null) {

                            roles.forEach(role ->
                                    mappedAuthorities.add(
                                            new SimpleGrantedAuthority(
                                                    "ROLE_" + role.toUpperCase()
                                            )
                                    )
                            );
                        }
                    }
                }
            });

            return mappedAuthorities;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(
                "https://lemur-9.cloud-iam.com/auth/realms/shoppiq"
        );
    }
}

