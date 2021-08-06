package com.deliveryjumper.service;

import static org.junit.jupiter.api.Assertions.*;

import com.deliveryjumper.domain.Address;
import com.deliveryjumper.domain.Member;
import com.deliveryjumper.domain.Member.Builder;
import com.deliveryjumper.domain.Role;
import com.deliveryjumper.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-06
 * Time : 오후 3:03
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@SpringBootTest
@Transactional
class MemberAuthServiceTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void memberJoinTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Member.Builder builder = new Builder();
        Member member1 = builder
            .email("test1@email.com")
            .role(Role.ROLE_CUSTOMER)
            .name("member1")
            .password(encoder.encode("1234"))
            .address(new Address("1234", "city1", "detail city1"))
            .build();

        Member saveMember = memberRepository.save(member1);
        Member findMember = memberRepository.findById(saveMember.getMemberId()).get();

        Assertions.assertThat(saveMember).isEqualTo(findMember);
        Assertions.assertThat(saveMember.getMemberId()).isEqualTo(findMember.getMemberId());
    }
}