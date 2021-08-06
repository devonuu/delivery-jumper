package com.deliveryjumper.service;

import com.deliveryjumper.domain.Member;
import com.deliveryjumper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-06
 * Time : 오후 3:49
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member join(Member member) {
        member.encodingPassword(
            passwordEncoder.encode(member.getPassword())
        );
        return memberRepository.save(member);
    }

    public Member findById(Long id){
        Member findMember = memberRepository.findById(id)
            .orElseThrow();
        return findMember;
    }
}
