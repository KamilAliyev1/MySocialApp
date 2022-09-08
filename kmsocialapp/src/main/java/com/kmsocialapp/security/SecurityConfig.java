package com.kmsocialapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserSecurityDetailService userSecurityDetailService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserSecurityDetailService userSecurityDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userSecurityDetailService = userSecurityDetailService;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.POST,"/profile").permitAll()
                .antMatchers("/api/v1/userprofiles/*").permitAll()
                .antMatchers("/api/v1/posts/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/profile",true)
                .and()
                .rememberMe()
                .key("secured")
                .tokenValiditySeconds(10)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID","remember-me")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(){
        var aut = new DaoAuthenticationProvider();
        aut.setPasswordEncoder(passwordEncoder);
        aut.setUserDetailsService(userSecurityDetailService);
        return new ProviderManager(aut);
    }
}
