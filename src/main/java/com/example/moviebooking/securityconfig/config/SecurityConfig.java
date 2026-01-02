package com.example.moviebooking.securityconfig.config;

import com.example.moviebooking.securityconfig.authprovider.JwtFilter;
import com.example.moviebooking.securityconfig.exception.JwtAccessDeniedHandler;
import com.example.moviebooking.securityconfig.exception.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    private final JwtFilter jwtFilter;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // ================= PUBLIC =================
                        .requestMatchers(
                                "/auth/**",
                                // Swagger URLs
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html" ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        // ================= USER MANAGEMENT =================
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        // ================= CITY (ADMIN ONLY) =================
                        .requestMatchers("/city/**").hasRole("ADMIN")

                        // ================= THEATRE =================
                        .requestMatchers(HttpMethod.POST, "/theatres/**").hasRole("THEATRE")
                        .requestMatchers(HttpMethod.PATCH, "/theatres/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/theatres/**").hasRole("THEATRE")
                        .requestMatchers(HttpMethod.GET, "/theatres/**")
                        .hasAnyRole("ADMIN", "CUSTOMER","THEATRE")

                        // ================= MOVIES =================
                        .requestMatchers(HttpMethod.POST, "/movies/**").hasRole("THEATRE")
                        .requestMatchers(HttpMethod.PUT, "/movies/**").hasRole("THEATRE")
                        .requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole("THEATRE")
                        .requestMatchers(HttpMethod.GET, "/movies/**")
                        .hasAnyRole("ADMIN", "CUSTOMER","THEATRE")

                        // ================= SHOWS =================
                        .requestMatchers(HttpMethod.POST, "/api/shows/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/shows/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/shows/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/shows/**")
                        .hasAnyRole("ADMIN", "CUSTOMER","THEATRE")

                        // ================= BOOKINGS =================
                        .requestMatchers("/bookings/**").hasRole("CUSTOMER")

                        // ================= PAYMENTS =================
                        .requestMatchers("/payments/**").hasRole("CUSTOMER")

                        // ================= ANY OTHER =================
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
