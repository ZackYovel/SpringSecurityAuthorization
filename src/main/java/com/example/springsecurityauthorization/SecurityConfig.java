package com.example.springsecurityauthorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // In order to avoid hard-coding passwords in our application code, which can later on be decompiled and read,
        // the default encoder takes pre-encoded passwords when new users are saved.
        // To find out the way the password encoder will encode a password we can use the next couple of lines.
//        System.out.println("{bcrypt}1234=" + PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"));
//        System.out.println("{bcrypt}Aa123456=" + PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("Aa123456"));


        auth.inMemoryAuthentication()
                .withUser("bob")
                .password("{bcrypt}$2a$10$9022rT8.kR4aZK3joqkT8.uUxX3ojEKNofL6jdNJ1pN8JGpZdf6Vq") // bcrypt encoding for '1234'
//                .password("{noop}1234") // Can be used for debug purposes: saves password as plain-text
                .roles("USER")
                .and()
                .withUser("alice")
                .password("{bcrypt}$2a$10$dS31pqO0CjRnOKRzZHi11u8BvLYVMhFndGx6BNYYiELI10sv8oHky") // bcrypt encoding for 'Aa123456'
//                .password("{noop}Aa123456") // Can be used for debug purposes: saves password as plain-text
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/secret/*").hasRole("ADMIN")
                .antMatchers("/*").authenticated()
                .and()
                .formLogin();
    }
}
