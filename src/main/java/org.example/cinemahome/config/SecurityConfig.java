package org.example.cinemahome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/api/public/**").permitAll()
                    .requestMatchers("/movies/**", "/films/**", "/api/movies/**").hasAnyRole("MODERATOR", "ADMIN")
                    .requestMatchers("/admin/**", "/users/**", "/api/admin/**").hasAnyRole("MODERATOR", "ADMIN")
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
