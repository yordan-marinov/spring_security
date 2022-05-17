package com.yordanm.securityspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.yordanm.securityspringboot.configuration.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new UsernamePasswordAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        final UserDetails mikeyMouseUser = User.builder()
                .username("mikeymouse")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();

        final UserDetails lindaAdmin = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password1122"))
                .roles(ADMIN.name())
                .build();

        final UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password1122"))
                .roles(ADMINTRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(mikeyMouseUser, lindaAdmin, tomUser);
    }
}
