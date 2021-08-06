package com.deliveryjumper.service;

import com.deliveryjumper.domain.Member;
import com.deliveryjumper.dto.AuthSessionDto;
import com.deliveryjumper.repository.MemberRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-05
 * Time : 오후 2:39
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

        member.orElseThrow(() -> new UsernameNotFoundException("Not Found : " + email));
        Member findMember = member.get();

        return member.map(AuthSessionDto::new).get();
    }
}