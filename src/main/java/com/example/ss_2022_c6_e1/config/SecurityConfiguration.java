package com.example.ss_2022_c6_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {



    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        var userBill = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        var userJohn = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("write", "read")
                .build();

        uds.createUser(userBill);
        uds.createUser(userJohn);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic().and()
//                .authorizeRequests(c-> c.anyRequest().authenticated())
                .authorizeHttpRequests()
                .requestMatchers("/demo").authenticated()
                .requestMatchers("/demo/*").hasAuthority("write")
                .requestMatchers(HttpMethod.GET, "/test/test2").hasAuthority("read")
                .requestMatchers(HttpMethod.POST, "/test/test2").hasAuthority("write")

                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .build();

    }

}
