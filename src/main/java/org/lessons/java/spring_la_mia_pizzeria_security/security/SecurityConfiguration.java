package org.lessons.java.spring_la_mia_pizzeria_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final DatabaseUserDetailsService userDetailsService;

    public SecurityConfiguration(DatabaseUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/pizze/**").hasAnyRole("USER", "ADMIN") // index e dettaglio pizza
                        .requestMatchers("/pizze/create", "/pizze/edit/**", "/pizze/delete/**").hasRole("ADMIN") // operazioni
                                                                                                                 // admin
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // pagina login personalizzata
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .userDetailsService(userDetailsService); // collega il tuo DatabaseUserDetailsService

        return http.build();
    }
}
