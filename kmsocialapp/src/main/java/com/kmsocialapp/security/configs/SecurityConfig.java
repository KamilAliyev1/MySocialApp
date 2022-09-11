package com.kmsocialapp.security.configs;

import com.kmsocialapp.security.CustomAuthFilter;
import com.kmsocialapp.security.CustomAuthorizationFilter;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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
        //CustomAuthFilter customAuthFilter = new CustomAuthFilter(authenticationManager());
        //customAuthFilter.setFilterProcessesUrl("/register");


        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/usersecuritydetails/").permitAll()
              //  .antMatchers("/api/v1/userprofiles/*").permitAll()
              //  .antMatchers("/api/v1/posts/*").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilter(new CustomAuthFilter(authenticationManager()));
        http.addFilterBefore(new CustomAuthorizationFilter(userSecurityDetailService), UsernamePasswordAuthenticationFilter.class);

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
