package com.example.teduniversite.security.services;

import com.example.teduniversite.security.jwt.AuthEntryPointJwt;
import com.example.teduniversite.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.teduniversite.security.services.UserDetailsImpl;
import com.example.teduniversite.security.services.UserDetailsServiceImpl;


@Configuration

public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(Auth authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // .antMatchers("/api/user/**").hasRole(RoleType.SUPER_ADMIN.name())
                .antMatchers("/**").permitAll()
                .antMatchers("/file-system/**").hasRole((RoleType.SUPER_ADMIN.toString()))
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
}
