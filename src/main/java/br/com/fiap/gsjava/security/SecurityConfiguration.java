package br.com.fiap.gsjava.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/usuario/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mensagem").permitAll()
                        .requestMatchers(HttpMethod.POST, "/localizacao").permitAll()
                        .requestMatchers(HttpMethod.POST, "/springia/generate").permitAll()
                        .requestMatchers(HttpMethod.GET, "/localizacao/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensagem/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensagem/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mensagem//paginacao/mensagens").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/mensagem/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/mensagem/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/localizacao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/localizacao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuario/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

