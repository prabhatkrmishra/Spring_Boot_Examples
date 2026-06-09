package CN.CompassionConnect.config;

import CN.CompassionConnect.model.User;
import CN.CompassionConnect.repository.UserRepository;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DonationSecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/register", "/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/all", true)
                        .userInfoEndpoint(userInfo ->
                                userInfo.userAuthoritiesMapper(userAuthoritiesMapper())
                        )
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt ->
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

            Object realmAccess = jwt.getClaims().get("realm_access");

            if (realmAccess != null) {

                LinkedTreeMap<String, List<String>> roleMap =
                        (LinkedTreeMap<String, List<String>>) realmAccess;

                List<String> roles = roleMap.get("roles");

                if (roles != null) {
                    authorities.addAll(
                            roles.stream()
                                    .map(role -> new SimpleGrantedAuthority(
                                            "ROLE_" + role.toUpperCase()))
                                    .collect(Collectors.toList())
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

                // KEYCLOAK SUPPORT
                if (authority instanceof OAuth2UserAuthority oauth2UserAuthority) {

                    Map<String, Object> attributes =
                            oauth2UserAuthority.getAttributes();

                    if (attributes.containsKey("realm_access")) {

                        Map<String, Object> realmAccess =
                                (Map<String, Object>) attributes.get("realm_access");

                        List<String> roles =
                                (List<String>) realmAccess.get("roles");

                        if (roles != null) {

                            mappedAuthorities.addAll(
                                    roles.stream()
                                            .map(role ->
                                                    new SimpleGrantedAuthority(
                                                            "ROLE_" + role.toUpperCase()
                                                    ))
                                            .collect(Collectors.toSet())
                            );
                        }
                    }
                }

                // GOOGLE SUPPORT
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {

                    String email =
                            oidcUserAuthority.getUserInfo().getEmail();

                    User user = userRepository.findByEmail(email);

                    if (user != null) {

                        user.getRoles().forEach(role ->
                                mappedAuthorities.add(
                                        new SimpleGrantedAuthority(
                                                role.getRoleName()
                                        )
                                )
                        );
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