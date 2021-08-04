package com.deliveryjumper.config.auth;

import com.deliveryjumper.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        //임시설정
        .antMatchers("/hostOrder").hasRole("HOST")
        .antMatchers("/customerOrder").hasRole("CUSTOMER")
        .antMatchers("/riderOrder").hasRole("RIDER")
        .antMatchers().permitAll()
        .and()
        .logout().logoutSuccessUrl("/")
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .userService(customOAuth2UserService);



  }

}
