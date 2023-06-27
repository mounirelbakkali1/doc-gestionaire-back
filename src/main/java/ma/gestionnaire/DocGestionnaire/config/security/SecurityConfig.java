package ma.gestionnaire.DocGestionnaire.config.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import ma.gestionnaire.DocGestionnaire.config.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests((a) -> {
                    try {
                        a
                                .requestMatchers("/api/auth/**").permitAll()

                                // .requestMatchers(HttpMethod.GET,"/api/v1/users").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and().oauth2ResourceServer(oauth2 -> oauth2.jwt());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private Filter authenticationJwtTokenFilter() {
        return jwtTokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
