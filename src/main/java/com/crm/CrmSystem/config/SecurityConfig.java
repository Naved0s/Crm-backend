package com.crm.CrmSystem.config;

import com.crm.CrmSystem.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomUserDetailService userDetailService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("http://localhost:4200")); // or "*", but avoid in prod
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true); // Needed if you're sending cookies or auth headers
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/", "/Signup","/Login","/LoginU","/leadsource/**","/service/**","/lead/**","/update/**", "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html","/leads/**","/all","/salesLeads/**","/updateNego/{id}","/client/**").permitAll()
                            //.requestMatchers("/","/signup","/userid","/login").permitAll()
                            //.requestMatchers("/userid").hasRole("ADMIN") // Requires ROLE_ADMIN
                            .anyRequest().authenticated()
                    ).userDetailsService(userDetailService);

            return http.build();
        }
}
