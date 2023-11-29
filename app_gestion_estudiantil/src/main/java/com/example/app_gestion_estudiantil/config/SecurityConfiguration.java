package com.example.app_gestion_estudiantil.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true,securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http.headers().frameOptions().sameOrigin();
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeHttpRequests()
                .requestMatchers("/api/user/**",
                        "/login2.html",
                        "/api/auth/**",
                        "/api/foros/**",
                        "/api/archivo/**",
                        "/api/calendario/**",
                        "/api/representantes/**",
                        "/inicio.html",
                        "/registro.html",
                        "/login.html",
                        "/foro.html",
                        "/foro_intento2.html",
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/api/comentario/**",
                        "/api/archivo/bajar/**",
                        "/PRUEBA.html"
                        )
                .permitAll()
                .anyRequest()
                .authenticated();

        http.cors().and().csrf().disable();

        http.authenticationProvider(authenticationProvider);
/*

        http.exceptionHandling().authenticationEntryPoint((request,response,ex)->{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
        System.out.println(ex.getMessage());

});*/
        http.addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
