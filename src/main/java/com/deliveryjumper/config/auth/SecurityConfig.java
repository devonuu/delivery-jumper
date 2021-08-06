package com.deliveryjumper.config.auth;

import com.deliveryjumper.handler.LoginFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService authService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService)
            .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/delivery/**").hasRole("RIDER")
            .antMatchers("/host/**").hasRole("HOST")
            .antMatchers("/member/**").authenticated()
            .antMatchers("/order/**").authenticated()
            .anyRequest().permitAll()
                .and()
            .formLogin()
            .failureHandler(loginFailureHandler())
                .and()
            .logout()
            .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    /**
     * 로그인 실패 시 handler
     */
    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }
}
