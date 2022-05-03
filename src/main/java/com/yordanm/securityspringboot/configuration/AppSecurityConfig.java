package com.yordanm.securityspringboot.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.yordanm.securityspringboot.configuration.UserRole.ADMIN;
import static com.yordanm.securityspringboot.configuration.UserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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
                .username("elvis")
                .password(passwordEncoder.encode("password1122"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(mikeyMouseUser, lindaAdmin);
    }
}
