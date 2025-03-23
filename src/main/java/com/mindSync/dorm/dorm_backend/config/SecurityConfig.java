package com.mindSync.dorm.dorm_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Enable CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers("/api/admin/dashboard").hasRole("ADMIN")
                        .requestMatchers("/api/user/profile").hasRole("USER")
                        .requestMatchers("/api/user/addrequest").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/user/getuserrequests").hasRole("USER")
                        .requestMatchers("/api/user/expenses/addexpenses").hasRole("USER")
                        .requestMatchers("/api/user/roomrequest").hasRole("USER")
                        .requestMatchers("/api/user/addprefrences").hasRole("USER")
                        .requestMatchers("/api/user/getprofiledetails").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/user/getuserprefrences").hasRole("USER")
                        .requestMatchers("/api/user/matchedprefrencesusers").hasRole("USER")
                        .requestMatchers("/api/user/getallactivatedservices").hasRole("USER")
                        .requestMatchers("/api/user/addprofiledetails").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/admin/rooms/addroom").hasRole("ADMIN")
                        .requestMatchers("/api/admin/services/addservice").hasRole("ADMIN")
                        .requestMatchers("/api/admin/getallusers").hasRole("ADMIN")
                        .requestMatchers("/api/admin/getallrooms").hasRole("ADMIN")
                        .requestMatchers("/api/admin/getallservices").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // ✅ Proper CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // ✅ Allow frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
