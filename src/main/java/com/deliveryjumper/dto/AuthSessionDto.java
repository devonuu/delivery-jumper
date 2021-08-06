package com.deliveryjumper.dto;

import com.deliveryjumper.domain.Member;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-05
 * Time : 오후 2:41
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@AllArgsConstructor
@NoArgsConstructor
public class AuthSessionDto implements UserDetails, Serializable {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public AuthSessionDto(Member member){
        this.username = member.getEmail();
        this.password = member.getPassword();
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
