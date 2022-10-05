package com.knubisoft.mypetproject.security;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return email -> {
            User user = userRepository.findByEmail(email);
            if (user != null)
                return user;
            throw new UsernameNotFoundException("User " +  email + " not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers( "/my/**").hasRole("USER")
                .antMatchers("/", "/search").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .and()
                .build();
    }
}
